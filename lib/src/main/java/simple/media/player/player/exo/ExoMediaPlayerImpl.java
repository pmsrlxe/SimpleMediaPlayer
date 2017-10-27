package simple.media.player.player.exo;

import android.content.Context;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.MediaPlayerActionFactory;
import simple.media.player.data.MediaParams;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.MediaListenerHolder;
import simple.media.player.listener.OnBufferChangeListener;
import simple.media.player.listener.OnPlayingBufferListener;
import simple.media.player.listener.OnStateChangeListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

/**
 * exo版本的实现
 * <p>
 * Created by rty on 27/10/2017.
 */

public class ExoMediaPlayerImpl implements SimpleMediaPlayer {
    private MediaListenerHolder listeners = new MediaListenerHolder();
    private ExoRealMediaPlayer realPlayer = null;
    private MediaParams mediaParams;
    private MediaPlayerAction currentAction;
    private MediaPlayerState currentState;
    private Context context;


    @Override
    public void initIfNeed(Context context) {
        this.context = context;
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);
        realPlayer = new ExoRealMediaPlayer(new DefaultRenderersFactory(context), trackSelector,
                new DefaultLoadControl());
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void reset(MediaParams mediaParams) {
        this.mediaParams = new MediaParams(mediaParams);
        perform(MediaPlayerState.Reset);
    }


    @Override
    public void prepare() {
        perform(MediaPlayerState.Prepared);
    }

    @Override
    public void start() {
        perform(MediaPlayerState.Playing);
    }

    @Override
    public void stop() {
        perform(MediaPlayerState.Stopped);
    }

    @Override
    public void pause() {
        perform(MediaPlayerState.Paused);
    }

    @Override
    public void release() {
        perform(MediaPlayerState.Released);
    }

    @Override
    public void seekToPercent(int percent) {
        mediaParams.setSeekToPercent(percent);
        perform(MediaPlayerState.Seeking);
    }

    @Override
    public synchronized MediaPlayerState getMediaPlayerState() {
        return currentState;
    }

    @Override
    public MediaParams getMediaParams() {
        return mediaParams;
    }

    @Override
    public int getDurationInMs() {
        return currentAction.getDurationMs();
    }

    @Override
    public int getCurrentPositionInMs() {
        return currentAction.getCurrentPositionMs();
    }

    @Override
    public RealMediaPlayer getRealMediaPlayer() {
        return realPlayer;
    }

    @Override
    public synchronized void setMediaPlayerStateFromAction(MediaPlayerState to) {
        MediaPlayerState from = currentState;
        currentState = to;
        listeners.notifyStateChangeListener(from, to);
    }

    @Override
    public void addOnPlayingBufferListener(OnPlayingBufferListener l) {
        listeners.addOnPlayingBufferListener(l);
    }

    @Override
    public void removeOnPlayingBufferListener(OnPlayingBufferListener l) {
        listeners.removeOnPlayingBufferListener(l);
    }

    @Override
    public void addOnStateChangeListener(OnStateChangeListener listener) {
        listeners.addOnStateChangeListener(listener);
    }

    @Override
    public void removeOnStateChangeListener(OnStateChangeListener listener) {
        listeners.removeOnStateChangeListener(listener);
    }

    @Override
    public void addOnBufferChangeListener(OnBufferChangeListener l) {
        listeners.addOnBufferChangeListener(l);
    }

    @Override
    public void removeOnBufferChangeListener(OnBufferChangeListener l) {
        listeners.removeOnBufferChangeListener(l);
    }

    private void perform(MediaPlayerState changeToState) {
        currentAction = MediaPlayerActionFactory.getAction(this, changeToState);
        currentAction.perform();
    }
}
