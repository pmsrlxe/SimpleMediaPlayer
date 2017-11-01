package simple.media.player.player.exo;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

import simple.media.player.data.MediaParams;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.BaseMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

/**
 * exo版本的实现
 * <p>
 * Created by rty on 27/10/2017.
 */

public class ExoMediaPlayerImpl extends BaseMediaPlayer<ExoRealMediaPlayer> {

    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (realMediaPlayer != null) {
                realMediaPlayer.setVideoSurfaceHolder(holder);
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            pause();
        }
    };

    public ExoMediaPlayerImpl(Context context) {
        super(context);
    }

    @Override
    protected ExoRealMediaPlayer initRealMediaPlayer() {

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        ExoRealMediaPlayer realPlayer = new ExoRealMediaPlayer(context, new DefaultRenderersFactory(context),
                trackSelector, new DefaultLoadControl());

        realPlayer.addListener(new EventListenerWrapper());
        return realPlayer;
    }

    @Override
    public void reset(MediaParams mediaParams) {
        super.reset(mediaParams);
        removeCallBack();
        mediaParams.getSurfaceView().getHolder().addCallback(callback);
        realMediaPlayer.setVideoSurfaceHolder(mediaParams.getSurfaceView().getHolder());
    }


    @Override
    public void release() {
        super.release();
        removeCallBack();
    }

    private void removeCallBack() {
        if (mediaParams != null && mediaParams.getSurfaceView() != null
                && mediaParams.getSurfaceView().getHolder() != null) {
            mediaParams.getSurfaceView().getHolder().removeCallback(callback);
        }
    }

    private class EventListenerWrapper implements Player.EventListener {
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
            Log.e(SimpleMediaPlayer.TAG, "onTimelineChanged");
        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray
                trackSelections) {
            Log.e(SimpleMediaPlayer.TAG, "onTracksChanged");
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
            Log.e(SimpleMediaPlayer.TAG, "onLoadingChanged,isLoading:" + isLoading);
            //exo的所有类型的buffer都会回调这个
            //所以需要区分类型是preparing的loading
            //还是seek的loading
            //没有prepared的不要回调
            if (!currentState.isHasDataState()) {
                return;
            }
            if (isLoading) {
                listenersHolder.notifyPauseForBuffer();
            } else {
                listenersHolder.notifyPlayingFromPause();
            }
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            Log.e(SimpleMediaPlayer.TAG, "onPlayerStateChanged,playWhenReady:"
                    + playWhenReady + ",playbackState:" + playbackState);
            if (currentState.isHasDataState()) {
                //seek结束了，会回调这个，变成ready就是seek结束了
                if (currentState == MediaPlayerState.Seeking && playbackState == Player.STATE_READY
                        //直接拖到最后了
                        || (currentState == MediaPlayerState.Seeking && playbackState == Player.STATE_ENDED)
                        ) {
                    listenersHolder.notifySeekComplete();
                    Log.e(SimpleMediaPlayer.TAG, "notifySeekComplete");
                }
            } else {
                //这个回调也不靠谱，不能作为prepared的依据
                //因为setPlayingState一旦调用了，playWhenReady状态变了，这个方法就会回调！
                //也就是说，调用pause之后start，就会回调这个方法，需要特殊处理下
                //已经prepared的不要回调（start，pause）
                if (playbackState == Player.STATE_READY) {
                    listenersHolder.notifyPrepared();
                    Log.e(SimpleMediaPlayer.TAG, "notifyPrepared");
                }
            }


            //播放结束
            if (playbackState == Player.STATE_ENDED) {
                listenersHolder.notifyComplete();
            }

        }

        @Override
        public void onRepeatModeChanged(int repeatMode) {
            Log.e(SimpleMediaPlayer.TAG, "onRepeatModeChanged:" + repeatMode);
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
            Log.e(SimpleMediaPlayer.TAG, "onPlayerError:" + error);
        }

        @Override
        public void onPositionDiscontinuity() {
            Log.e(SimpleMediaPlayer.TAG, "onPositionDiscontinuity");
        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Log.e(SimpleMediaPlayer.TAG, "onPlaybackParametersChanged");
        }
    }
}
