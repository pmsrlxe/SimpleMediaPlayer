package simple.media.player.player;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.util.Log;

import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.MediaPlayerActionFactory;
import simple.media.player.data.MediaParams;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.MediaListenersHolder;
import simple.media.player.listener.OnBufferChangeListener;
import simple.media.player.listener.OnCompleteListener;
import simple.media.player.listener.OnErrorListener;
import simple.media.player.listener.OnPlayingBufferListener;
import simple.media.player.listener.OnPreparedListener;
import simple.media.player.listener.OnSeekCompleteListener;
import simple.media.player.listener.OnStateChangeListener;

/**
 * 就处理了公用部分
 * <p>
 * Created by rty on 30/10/2017.
 */

public abstract class BaseMediaPlayer<T extends RealMediaPlayer> implements SimpleMediaPlayer {
    protected final Context context;
    protected final MediaListenersHolder listenersHolder = new MediaListenersHolder();
    protected MediaPlayerAction currentAction;
    protected MediaPlayerState currentState = MediaPlayerState.Init;
    protected MediaParams mediaParams;
    protected T realMediaPlayer;

    public BaseMediaPlayer(Context context) {
        this.context = context;
        realMediaPlayer = initRealMediaPlayer();
    }

    protected abstract T initRealMediaPlayer();

    /**
     * 准备播放
     */
    @Override
    public void prepare() {
        perform(MediaPlayerState.Prepared);
    }

    /**
     * 播放
     */
    @Override
    public void start() {
        perform(MediaPlayerState.Playing);
    }

    /**
     * 停止
     */
    @Override
    public void stop() {
        perform(MediaPlayerState.Stopped);
    }

    /**
     * 暂停
     */
    @Override
    public void pause() {
        perform(MediaPlayerState.Paused);
    }

    /**
     * 释放
     */
    @Override
    public void release() {
        listenersHolder.release();
        perform(MediaPlayerState.Released);
    }

    @Override
    public synchronized MediaPlayerState getMediaPlayerState() {
        return currentState;
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

        perform(MediaPlayerState.Seeking);
    }

    @Override
    public void reset(MediaParams mediaParams) {
        this.mediaParams = new MediaParams(mediaParams);
        perform(MediaPlayerState.Reset);
    }

    @Override
    public MediaParams getMediaParams() {
        return mediaParams;
    }


    @Override
    public RuntimeInfo getRuntimeInfo() {
        return new RuntimeInfo(getCurrentPositionInMs(), getDurationInMs(), currentState);
    }

    private long getDurationInMs() {
        if (!currentState.isHasDataState() || realMediaPlayer == null) {
            return 0;
        }
        try {
            return realMediaPlayer.doGetDurationMs();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return 0;
        }
    }

    private long getCurrentPositionInMs() {
        if (!currentState.isHasDataState() || realMediaPlayer == null) {
            return 0;
        }
        try {
            return realMediaPlayer.doGetCurrentPositionMs();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return 0;
        }
    }

    private void perform(MediaPlayerState changeToState) {
        if (changeToState == currentState) {
            Log.e(TAG, "Same perform!(" + changeToState + ")");
            return;
        }
        if (currentAction != null) {
            currentAction.onRelease();
        }
        currentAction = MediaPlayerActionFactory.getAction(this, realMediaPlayer, changeToState);
        currentAction.perform();
    }


    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public synchronized void setMediaPlayerStateFromAction(MediaPlayerState toState) {
        MediaPlayerState from = this.currentState;
        this.currentState = toState;
        listenersHolder.notifyStateChangeListener(from, toState);
        Log.i(SimpleMediaPlayer.TAG, "setMediaPlayerState " + from + "->" + toState);
    }

    /*--------------------------------listeners---------------------------------------*/

    @Override
    public void addOnPlayingBufferListener(OnPlayingBufferListener l) {
        listenersHolder.addOnPlayingBufferListener(l);
    }

    @Override
    public void removeOnPlayingBufferListener(OnPlayingBufferListener l) {
        listenersHolder.removeOnPlayingBufferListener(l);
    }

    @Override
    public void addOnStateChangeListener(OnStateChangeListener l) {
        listenersHolder.addOnStateChangeListener(l);
    }

    @Override
    public void removeOnStateChangeListener(OnStateChangeListener l) {
        listenersHolder.removeOnStateChangeListener(l);
    }

    @Override
    public void addOnBufferChangeListener(OnBufferChangeListener l) {
        listenersHolder.addOnBufferChangeListener(l);
    }

    @Override
    public void removeOnBufferChangeListener(OnBufferChangeListener l) {
        listenersHolder.removeOnBufferChangeListener(l);
    }

    @Override
    public void addOnPreparedListener(OnPreparedListener l) {
        listenersHolder.addOnPreparedListener(l);
    }

    @Override
    public void removeOnPreparedListener(OnPreparedListener l) {
        listenersHolder.removeOnPreparedListener(l);
    }

    @Override
    public void addOnSeekCompleteListener(OnSeekCompleteListener l) {
        listenersHolder.addOnSeekCompleteListener(l);
    }

    @Override
    public void removeOnSeekCompleteListener(OnSeekCompleteListener l) {
        listenersHolder.removeOnSeekCompleteListener(l);
    }

    @Override
    public void addOnCompleteListener(OnCompleteListener l) {
        listenersHolder.addOnCompleteListener(l);
    }

    @Override
    public void removeOnCompleteListener(OnCompleteListener l) {
        listenersHolder.removeOnCompleteListener(l);
    }

    @Override
    public void addOnErrorListener(OnErrorListener l) {
        listenersHolder.addOnErrorListener(l);
    }

    @Override
    public void removeOnErrorListener(OnErrorListener l) {
        listenersHolder.removeOnErrorListener(l);
    }

}
