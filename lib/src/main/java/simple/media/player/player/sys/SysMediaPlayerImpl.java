package simple.media.player.player.sys;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.SurfaceHolder;

import simple.media.player.data.MediaParams;
import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.listener.OnPlayingBufferListener;
import simple.media.player.player.BaseMediaPlayer;

/**
 * 系统自带的MediaPlayer实现
 */
public class SysMediaPlayerImpl extends BaseMediaPlayer<SysRealMediaPlayer> {
    private SurfaceCallBackWrapper surfaceCallBackWrapper;

    public SysMediaPlayerImpl(Context context) {
        super(context);
    }

    @Override
    protected SysRealMediaPlayer initRealMediaPlayer() {
        SysRealMediaPlayer mediaPlayer = new SysRealMediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnCompletionListener(new OnCompletionListenerWrapper());
        mediaPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListenerWrapper());
        mediaPlayer.setOnSeekCompleteListener(new OnSeekCompleteListenerWrapper());
        mediaPlayer.setOnErrorListener(new OnErrorListenerWrapper());
        mediaPlayer.setOnInfoListener(new OnInfoListenerWrapper());
        mediaPlayer.setOnPreparedListener(new OnPreparedListenerWrapper());
        return mediaPlayer;
    }


    /**
     * 重置player
     */
    @Override
    public void reset(MediaParams mediaParams) {
        super.reset(mediaParams);
        this.surfaceCallBackWrapper = new SurfaceCallBackWrapper();
        this.mediaParams.getSurfaceView().getHolder().addCallback(surfaceCallBackWrapper);
    }


    /**
     * 释放
     */
    @Override
    public void release() {
        super.release();
        if (surfaceCallBackWrapper != null) {
            this.mediaParams.getSurfaceView().getHolder().removeCallback(surfaceCallBackWrapper);
        }
    }

    /*--------------------------------listener wrapper---------------------------------------*/

    private class OnPreparedListenerWrapper implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            Log.i(TAG, "onPrepared");
            listenersHolder.notifyPrepared();
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
                    listenersHolder.notifyPauseForBuffer();
                }

                @Override
                public void onPlayingFromPause() {
                    listenersHolder.notifyPlayingFromPause();
                }
            });
            return true;
        }
    }

    private class OnErrorListenerWrapper implements MediaPlayer.OnErrorListener {

        @Override
        public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
            Log.e(TAG, "onError,w:" + what + ",e:" + extra);
            listenersHolder.notifyError(new MediaPlayerError(what, extra));
            return true;
        }
    }

    private class OnSeekCompleteListenerWrapper implements MediaPlayer.OnSeekCompleteListener {

        @Override
        public void onSeekComplete(MediaPlayer mediaPlayer) {
            Log.e(TAG, "onSeekComplete");
            listenersHolder.notifySeekComplete();
        }
    }

    private class OnBufferingUpdateListenerWrapper implements MediaPlayer.OnBufferingUpdateListener {

        @Override
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
            Log.i(TAG, "onBufferingUpdate,pc:" + percent);
            listenersHolder.notifyBufferingUpdate(percent);
        }
    }

    private class OnCompletionListenerWrapper implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Log.e(TAG, "onCompletion");
            listenersHolder.notifyComplete();
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
