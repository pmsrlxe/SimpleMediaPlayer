package simple.media.player.player.sys;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;

import simple.media.player.data.MediaParams;
import simple.media.player.player.RealMediaPlayer;

/**
 * 为了查找问题写的，主要是打印log，无任何逻辑
 */
public class SysRealMediaPlayer extends MediaPlayer implements RealMediaPlayer {

    private static final String TAG = "logMedia";

    public SysRealMediaPlayer() {
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
                Log.d(TAG, "onPrepared:" + SysRealMediaPlayer.this);
            }
        });
    }

    @Override
    public void setOnInfoListener(final OnInfoListener listener) {
        super.setOnInfoListener(new OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                Log.d(TAG, "onInfo(w=" + what + ",e=" + extra + "):" + SysRealMediaPlayer.this);
                return listener != null && listener.onInfo(mp, what, extra);
            }
        });
    }

    @Override
    public void setDisplay(SurfaceHolder sh) {
        super.setDisplay(sh);
        Log.d(TAG, "setDisplay(hd:" + sh + "):" + SysRealMediaPlayer.this);
    }

    @Override
    public void doSeekTo(int positionMs) throws IllegalStateException {
        super.seekTo(positionMs);
        Log.d(TAG, "doSeekTo(" + positionMs + "):" + SysRealMediaPlayer.this);
    }

    @Override
    public void doPrepareAsync(MediaParams params) throws IOException, IllegalStateException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            setDataSource(params.getContext(), Uri.parse(params.getUrl()));
        } else {
            setDataSource(params.getUrl());
        }

        super.prepare();
        Log.d(TAG, "doPrepare:" + this);
    }

    @Override
    public void doStop() throws IllegalStateException {
        super.stop();
        Log.d(TAG, "doStop:" + this);
    }

    @Override
    public void doStart() throws IllegalStateException {
        super.start();
        Log.d(TAG, "doStart:" + this);
    }

    @Override
    public void doPause() throws IllegalStateException {
        super.pause();
        Log.d(TAG, "doPause:" + this);
    }

    @Override
    public long doGetCurrentPositionMs() throws Throwable {
        Log.d(TAG, "doGetCurrentPositionMs:" + this);
        return super.getCurrentPosition();
    }

    @Override
    public long doGetDurationMs() throws Throwable {
        Log.d(TAG, "doGetDurationMs:" + this);
        return super.getDuration();
    }

    @Override
    public void doRelease() {
        super.release();
        Log.d(TAG, "doRelease:" + this);
    }

    @Override
    public void doReset() throws Throwable {
        reset();
        Log.d(TAG, "doResetAndSetSource:" + this);
    }

}
