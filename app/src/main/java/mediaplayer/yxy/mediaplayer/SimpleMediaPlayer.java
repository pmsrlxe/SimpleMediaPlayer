package mediaplayer.yxy.mediaplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
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
import mediaplayer.yxy.mediaplayer.listener.OnBufferStateListener;

public class SimpleMediaPlayer {
    public static final String TAG = "SimpleMediaPlayer";
    private MediaPlayer mediaPlayer;
    private MediaPlayerState mediaPlayerState = MediaPlayerState.Init;
    private MediaPlayerAction mediaPlayerAction;
    private MediaParams mediaParams;
    private List<OnMediaPlayerStateChangeListener> stateChangeListeners = new ArrayList<>();
    private OnBufferChangeListener onBufferChangeListener;
    private OnBufferStateListener onBufferStateListener;
    private SurfaceCallBackWrapper surfaceCallBackWrapper;

    public void MediaPlayer() {
    }

    public MediaPlayer getRealMediaPlayer() {
        return mediaPlayer;
    }

    //重置
    public void reset(MediaParams mediaParams) {
        this.mediaParams = new MediaParams(mediaParams);
        this.surfaceCallBackWrapper = new SurfaceCallBackWrapper();
        this.mediaParams.getSurfaceView().getHolder().addCallback(surfaceCallBackWrapper);
        perform(true, MediaPlayerState.Reset);
    }

    //准备
    public void prepare() {
        perform(true, MediaPlayerState.Prepared);
    }

    //播放
    public void start() {
        perform(true, MediaPlayerState.Playing);
    }

    //停止
    public void stop() {
        perform(true, MediaPlayerState.Stopped);
    }

    //暂停
    public void pause() {
        perform(true, MediaPlayerState.Paused);
    }

    //释放
    public void release() {
        if (surfaceCallBackWrapper != null) {
            this.mediaParams.getSurfaceView().getHolder().removeCallback(surfaceCallBackWrapper);
        }
        stateChangeListeners.clear();
        perform(false, MediaPlayerState.Released);
    }

    //跳转 0-100
    public void seekToPercent(int percent) {
        mediaParams.setSeekToPercent(percent);
        perform(false, MediaPlayerState.SeekComplete);
    }

    public synchronized MediaPlayerState getMediaPlayerState() {
        return mediaPlayerState;
    }

    public synchronized void setMediaPlayerState(MediaPlayerState toState) {
        MediaPlayerState from = this.mediaPlayerState;
        this.mediaPlayerState = toState;
        if (stateChangeListeners != null) {
            for (OnMediaPlayerStateChangeListener l : stateChangeListeners) {
                l.onStateChange(from, toState);
            }
        }
        Log.i(SimpleMediaPlayer.TAG, "setMediaPlayerState " + from + "->" + toState);

    }

    public void setOnBufferChangeListener(OnBufferChangeListener onBufferChangeListener) {
        this.onBufferChangeListener = onBufferChangeListener;
    }

    public MediaParams getMediaParams() {
        return mediaParams;
    }

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
            mediaPlayer = new LogMediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new OnCompletionListenerWrapper());
            mediaPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListenerWrapper());
            mediaPlayer.setOnSeekCompleteListener(new OnSeekCompleteListenerWrapper());
            mediaPlayer.setOnErrorListener(new OnErrorListenerWrapper());
            mediaPlayer.setOnInfoListener(new OnInfoListenerWrapper());
            mediaPlayer.setOnPreparedListener(new OnPreparedListenerWrapper());
        }
    }

    public void setOnBufferStateListener(OnBufferStateListener bufferStateListener) {
        this.onBufferStateListener = bufferStateListener;
    }

    public void addOnMediaPlayerStateChangeListener(OnMediaPlayerStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        stateChangeListeners.add(listener);
    }

    public void removeOnMediaPlayerStateChangeListener(OnMediaPlayerStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        stateChangeListeners.remove(listener);
    }

    public int getDuration() {
        if (mediaPlayerAction == null) {
            return 0;
        }
        return mediaPlayerAction.getDuration();
    }

    public int getCurrentPosition() {
        if (mediaPlayerAction == null) {
            return 0;
        }
        return mediaPlayerAction.getCurrentPosition();
    }

    /*--------------------------------listener wrapper---------------------------------------*/

    private class OnPreparedListenerWrapper implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            Log.i(TAG, "onPrepared");
            mediaPlayerAction.onPrepared(SimpleMediaPlayer.this);
        }
    }

    private class OnInfoListenerWrapper implements MediaPlayer.OnInfoListener {

        @Override
        public boolean onInfo(MediaPlayer mediaPlayer, int what, int extra) {
            MediaPlayerInfo info = new MediaPlayerInfo(what, extra);
            Log.e(TAG, "onInfo," + info);
            info.callback(onBufferStateListener);
            return mediaPlayerAction != null && mediaPlayerAction.onInfo(SimpleMediaPlayer.this, info);
        }
    }

    private class OnErrorListenerWrapper implements MediaPlayer.OnErrorListener {

        @Override
        public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
            Log.e(TAG, "onError,w:" + what + ",e:" + extra);
            return mediaPlayerAction != null && mediaPlayerAction.onError(SimpleMediaPlayer.this,
                    new MediaPlayerError(what, extra));
        }
    }

    private class OnSeekCompleteListenerWrapper implements MediaPlayer.OnSeekCompleteListener {

        @Override
        public void onSeekComplete(MediaPlayer mediaPlayer) {
            Log.e(TAG, "onSeekComplete");
            if (mediaPlayerAction != null) {
                mediaPlayerAction.onSeekComplete(SimpleMediaPlayer.this);
            }
        }
    }

    private class OnBufferingUpdateListenerWrapper implements MediaPlayer.OnBufferingUpdateListener {

        @Override
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
            Log.i(TAG, "onBufferingUpdate,pc:" + percent);
            if (onBufferChangeListener != null) {
                onBufferChangeListener.onBufferUpdate(percent);
            }
            if (mediaPlayerAction != null) {
                mediaPlayerAction.onBufferingUpdate(SimpleMediaPlayer.this, percent);
            }
        }
    }

    private class OnCompletionListenerWrapper implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Log.e(TAG, "onCompletion");
            if (mediaPlayerAction != null) {
                mediaPlayerAction.onCompletion(SimpleMediaPlayer.this);
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
