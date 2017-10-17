package mediaplayer.yxy.mediaplayer.media;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.RestrictTo;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerActionFactory;
import mediaplayer.yxy.mediaplayer.data.MediaParams;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.listener.OnBufferChangeListener;
import mediaplayer.yxy.mediaplayer.listener.OnPlayingBufferListener;
import mediaplayer.yxy.mediaplayer.listener.OnStateChangeListener;

public class SimpleMediaPlayerImpl implements SimpleMediaPlayer {
    private MediaPlayer mediaPlayer;
    private MediaPlayerState mediaPlayerState = MediaPlayerState.Init;
    private MediaPlayerAction mediaPlayerAction;
    private MediaParams mediaParams;
    private SurfaceCallBackWrapper surfaceCallBackWrapper;

    private List<OnStateChangeListener> stateChangeListeners = new ArrayList<>();
    private List<OnBufferChangeListener> onBufferChangeListeners = new ArrayList<>();
    private List<OnPlayingBufferListener> onPlayingBufferListeners = new ArrayList<>();

    public void MediaPlayer() {
    }


    /**
     * 重置player
     */
    @Override
    public void reset(MediaParams mediaParams) {
        this.mediaParams = new MediaParams(mediaParams);
        this.surfaceCallBackWrapper = new SurfaceCallBackWrapper();
        this.mediaParams.getSurfaceView().getHolder().addCallback(surfaceCallBackWrapper);
        perform(true, MediaPlayerState.Reset);
    }

    /**
     * 准备播放
     */
    @Override
    public void prepare() {
        perform(true, MediaPlayerState.Prepared);
    }

    /**
     * 播放
     */
    @Override
    public void start() {
        perform(true, MediaPlayerState.Playing);
    }

    /**
     * 停止
     */
    @Override
    public void stop() {
        perform(true, MediaPlayerState.Stopped);
    }

    /**
     * 暂停
     */
    @Override
    public void pause() {
        perform(true, MediaPlayerState.Paused);
    }

    /**
     * 释放
     */
    @Override
    public void release() {
        if (surfaceCallBackWrapper != null) {
            this.mediaParams.getSurfaceView().getHolder().removeCallback(surfaceCallBackWrapper);
        }
        stateChangeListeners.clear();
        perform(false, MediaPlayerState.Released);
    }

    /**
     * 跳转 0-100
     */
    @Override
    public void seekToPercent(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("percent=" + percent + " is not correct,range should in [0-100]");
        }
        mediaParams.setSeekToPercent(percent);
        perform(false, MediaPlayerState.SeekComplete);
    }

    /**
     * 获取当前player的状态
     */
    @Override
    public synchronized MediaPlayerState getMediaPlayerState() {
        return mediaPlayerState;
    }

    /**
     * 获取当前player的参数
     */
    @Override
    public MediaParams getMediaParams() {
        return mediaParams;
    }

    /**
     * 获取当前的player长度
     *
     * @return 0说明没有
     */
    @Override
    public int getDurationInMs() {
        if (mediaPlayerAction == null) {
            return 0;
        }
        return mediaPlayerAction.getDuration();
    }

    /**
     * 获取当前播放的位置
     *
     * @return 0说明没有
     */
    @Override
    public int getCurrentPositionInMs() {
        if (mediaPlayerAction == null) {
            return 0;
        }
        return mediaPlayerAction.getCurrentPosition();
    }

    /*--------------------------------内部使用方法---------------------------------------*/

    private void perform(boolean init, MediaPlayerState changeToState) {
        if (init) {
            initIfNeed();
        }
        mediaPlayerAction = MediaPlayerActionFactory.getAction(this, changeToState);
        mediaPlayerAction.perform();
    }

    //初始化
    private void initIfNeed() {
        if (mediaPlayer == null) {
            mediaPlayer = new WrapMediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new OnCompletionListenerWrapper());
            mediaPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListenerWrapper());
            mediaPlayer.setOnSeekCompleteListener(new OnSeekCompleteListenerWrapper());
            mediaPlayer.setOnErrorListener(new OnErrorListenerWrapper());
            mediaPlayer.setOnInfoListener(new OnInfoListenerWrapper());
            mediaPlayer.setOnPreparedListener(new OnPreparedListenerWrapper());
        }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public synchronized void setMediaPlayerState(MediaPlayerState toState) {
        MediaPlayerState from = this.mediaPlayerState;
        this.mediaPlayerState = toState;
        if (stateChangeListeners != null) {
            for (OnStateChangeListener l : stateChangeListeners) {
                l.onStateChange(from, toState);
            }
        }
        Log.i(SimpleMediaPlayer.TAG, "setMediaPlayerState " + from + "->" + toState);

    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public MediaPlayer getRealMediaPlayer() {
        return mediaPlayer;
    }

    /*--------------------------------listeners---------------------------------------*/
    @Override
    public void addOnPlayingBufferListener(OnPlayingBufferListener l) {
        onPlayingBufferListeners.add(l);
    }

    @Override
    public void removeOnPlayingBufferListener(OnPlayingBufferListener l) {
        onPlayingBufferListeners.remove(l);
    }

    @Override
    public void addOnStateChangeListener(OnStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        stateChangeListeners.add(listener);
    }

    @Override
    public void removeOnStateChangeListener(OnStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        stateChangeListeners.remove(listener);
    }

    @Override
    public void addOnBufferChangeListener(OnBufferChangeListener l) {
        onBufferChangeListeners.add(l);
    }

    @Override
    public void removeOnBufferChangeListener(OnBufferChangeListener l) {
        onBufferChangeListeners.remove(l);
    }

    /*--------------------------------listener wrapper---------------------------------------*/

    private class OnPreparedListenerWrapper implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            Log.i(TAG, "onPrepared");
            mediaPlayerAction.onPrepared(SimpleMediaPlayerImpl.this);
        }
    }

    private class OnInfoListenerWrapper implements MediaPlayer.OnInfoListener {

        @Override
        public boolean onInfo(MediaPlayer mediaPlayer, int what, int extra) {
            MediaPlayerInfo info = new MediaPlayerInfo(what, extra);
            Log.e(TAG, "onInfo," + info);
            //回调
            for (OnPlayingBufferListener listener : onPlayingBufferListeners) {
                info.callback(listener);
            }
            return mediaPlayerAction != null && mediaPlayerAction.onInfo(SimpleMediaPlayerImpl.this, info);
        }
    }

    private class OnErrorListenerWrapper implements MediaPlayer.OnErrorListener {

        @Override
        public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
            Log.e(TAG, "onError,w:" + what + ",e:" + extra);
            return mediaPlayerAction != null && mediaPlayerAction.onError(SimpleMediaPlayerImpl.this,
                    new MediaPlayerError(what, extra));
        }
    }

    private class OnSeekCompleteListenerWrapper implements MediaPlayer.OnSeekCompleteListener {

        @Override
        public void onSeekComplete(MediaPlayer mediaPlayer) {
            Log.e(TAG, "onSeekComplete");
            if (mediaPlayerAction != null) {
                mediaPlayerAction.onSeekComplete(SimpleMediaPlayerImpl.this);
            }
        }
    }

    private class OnBufferingUpdateListenerWrapper implements MediaPlayer.OnBufferingUpdateListener {

        @Override
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
            Log.i(TAG, "onBufferingUpdate,pc:" + percent);
            if (mediaPlayerAction != null) {
                mediaPlayerAction.onBufferingUpdate(SimpleMediaPlayerImpl.this, percent);
            }
            //通知回调
            for (OnBufferChangeListener l : onBufferChangeListeners) {
                l.onBufferUpdate(percent);
            }
        }
    }

    private class OnCompletionListenerWrapper implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Log.e(TAG, "onCompletion");
            if (mediaPlayerAction != null) {
                mediaPlayerAction.onCompletion(SimpleMediaPlayerImpl.this);
            }
        }
    }

    private class SurfaceCallBackWrapper implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            getRealMediaPlayer().setDisplay(holder);
            //setScreenOnWhilePlaying(true) is ineffective without a SurfaceHolder
            getRealMediaPlayer().setScreenOnWhilePlaying(true);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            pause();
        }
    }
}
