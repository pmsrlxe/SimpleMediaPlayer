package simple.media.player.player.sys;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.RestrictTo;
import android.util.Log;
import android.view.SurfaceHolder;

import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.MediaPlayerActionFactory;
import simple.media.player.data.MediaParams;
import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.listener.MediaListenerHolder;
import simple.media.player.listener.OnBufferChangeListener;
import simple.media.player.listener.OnPlayingBufferListener;
import simple.media.player.listener.OnStateChangeListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

/**
 * 系统自带的MediaPlayer实现
 */
public class SysMediaPlayerImpl implements SimpleMediaPlayer {
    private SysRealMediaPlayer realMediaPlayer;
    private MediaPlayerState mediaPlayerState = MediaPlayerState.Init;
    private MediaPlayerAction mediaPlayerAction;
    private MediaParams mediaParams;
    private SurfaceCallBackWrapper surfaceCallBackWrapper;
    private MediaListenerHolder listeners = new MediaListenerHolder();

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
        perform(false, MediaPlayerState.Released);
        listeners.release();
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
        if (realMediaPlayer == null) {
            realMediaPlayer = new SysRealMediaPlayer();
            realMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            realMediaPlayer.setOnCompletionListener(new OnCompletionListenerWrapper());
            realMediaPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListenerWrapper());
            realMediaPlayer.setOnSeekCompleteListener(new OnSeekCompleteListenerWrapper());
            realMediaPlayer.setOnErrorListener(new OnErrorListenerWrapper());
            realMediaPlayer.setOnInfoListener(new OnInfoListenerWrapper());
            realMediaPlayer.setOnPreparedListener(new OnPreparedListenerWrapper());
        }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public synchronized void setMediaPlayerStateFromAction(MediaPlayerState toState) {
        MediaPlayerState from = this.mediaPlayerState;
        this.mediaPlayerState = toState;
        listeners.notifyStateChangeListener(from, toState);
        Log.i(SimpleMediaPlayer.TAG, "setMediaPlayerState " + from + "->" + toState);
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public RealMediaPlayer getRealMediaPlayer() {
        return realMediaPlayer;
    }

    /*--------------------------------listeners---------------------------------------*/
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

    /*--------------------------------listener wrapper---------------------------------------*/

    private class OnPreparedListenerWrapper implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            Log.i(TAG, "onPrepared");
            mediaPlayerAction.onPrepared(SysMediaPlayerImpl.this);
        }
    }

    private class OnInfoListenerWrapper implements MediaPlayer.OnInfoListener {

        @Override
        public boolean onInfo(MediaPlayer mediaPlayer, int what, int extra) {
            MediaPlayerInfo info = new MediaPlayerInfo(what, extra);
            Log.e(TAG, "onInfo," + info);
            //回调
            info.callback(new OnPlayingBufferListener() {
                @Override
                public void onPauseForBuffer() {
                    listeners.notifyPauseForBuffer();
                }

                @Override
                public void onPlayingFromPause() {
                    listeners.notifyPlayingFromPause();
                }
            });
            return mediaPlayerAction != null && mediaPlayerAction.onInfo(SysMediaPlayerImpl.this, info);
        }
    }

    private class OnErrorListenerWrapper implements MediaPlayer.OnErrorListener {

        @Override
        public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
            Log.e(TAG, "onError,w:" + what + ",e:" + extra);
            return mediaPlayerAction != null && mediaPlayerAction.onError(SysMediaPlayerImpl.this,
                    new MediaPlayerError(what, extra));
        }
    }

    private class OnSeekCompleteListenerWrapper implements MediaPlayer.OnSeekCompleteListener {

        @Override
        public void onSeekComplete(MediaPlayer mediaPlayer) {
            Log.e(TAG, "onSeekComplete");
            if (mediaPlayerAction != null) {
                mediaPlayerAction.onSeekComplete(SysMediaPlayerImpl.this);
            }
        }
    }

    private class OnBufferingUpdateListenerWrapper implements MediaPlayer.OnBufferingUpdateListener {

        @Override
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
            Log.i(TAG, "onBufferingUpdate,pc:" + percent);
            if (mediaPlayerAction != null) {
                mediaPlayerAction.onBufferingUpdate(SysMediaPlayerImpl.this, percent);
            }
            listeners.notifyBufferingUpdate(percent);
        }
    }

    private class OnCompletionListenerWrapper implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Log.e(TAG, "onCompletion");
            if (mediaPlayerAction != null) {
                mediaPlayerAction.onCompletion(SysMediaPlayerImpl.this);
            }
        }
    }

    private class SurfaceCallBackWrapper implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            realMediaPlayer.setDisplay(holder);
            //setScreenOnWhilePlaying(true) is ineffective without a SurfaceHolder
            realMediaPlayer.setScreenOnWhilePlaying(true);
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
