package mediaplayer.yxy.mediaplayer.media;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * 为了查找问题写的，主要是打印log，无任何逻辑
 */
public class WrapMediaPlayer extends MediaPlayer {

    private static final String TAG = "logMedia";

    public WrapMediaPlayer() {
        Log.d(TAG, "new:" + this);
    }

    @Override
    public void setOnPreparedListener(final OnPreparedListener listener) {
        super.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (listener != null) {
                    listener.onPrepared(mp);
                }
                Log.d(TAG, "onPrepared:" + WrapMediaPlayer.this);
            }
        });
    }

    @Override
    public void setOnInfoListener(final OnInfoListener listener) {
        super.setOnInfoListener(new OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                Log.d(TAG, "onInfo(w=" + what + ",e=" + extra + "):" + WrapMediaPlayer.this);
                return listener != null && listener.onInfo(mp, what, extra);
            }
        });
    }

    @Override
    public void setDisplay(SurfaceHolder sh) {
        super.setDisplay(sh);
        Log.d(TAG, "setDisplay(hd:" + sh + "):" + WrapMediaPlayer.this);
    }

    @Override
    public void seekTo(int msec) throws IllegalStateException {
        super.seekTo(msec);
        Log.d(TAG, "seekTo(" + msec + "):" + WrapMediaPlayer.this);
    }

    @Override
    public void prepare() throws IOException, IllegalStateException {
        super.prepare();
        Log.d(TAG, "prepare:" + this);
    }

    @Override
    public void stop() throws IllegalStateException {
        super.stop();
        Log.d(TAG, "stop:" + this);
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();
        Log.d(TAG, "start:" + this);
    }

    @Override
    public void pause() throws IllegalStateException {
        super.pause();
        Log.d(TAG, "pause:" + this);
    }

    @Override
    public void release() {
        super.release();
        Log.d(TAG, "release:" + this);
    }

}
