package simple.media.player.action;


import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;

import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.MediaPlayerStateAware;
import simple.media.player.media.SimpleMediaPlayerImpl;

public abstract class MediaPlayerAction implements MediaPlayerStateAware {
    private final SimpleMediaPlayerImpl simpleMediaPlayer;
    private final MediaPlayerState changeToState;
    private final MediaPlayerState fromState;
    private final MediaPlayer realMediaPlayer;
    private final Handler handler;

    public MediaPlayerAction(SimpleMediaPlayerImpl simpleMediaPlayer, MediaPlayerState changeToState) {
        this.simpleMediaPlayer = simpleMediaPlayer;
        this.realMediaPlayer = simpleMediaPlayer.getRealMediaPlayer();
        this.fromState = simpleMediaPlayer.getMediaPlayerState();
        this.changeToState = changeToState;
        this.handler = new Handler(Looper.getMainLooper());
    }

    public MediaPlayer getRealMediaPlayer() {
        return realMediaPlayer;
    }

    public MediaPlayerState getFromState() {
        return fromState;
    }

    public MediaPlayerState getChangeToState() {
        return changeToState;
    }

    public SimpleMediaPlayerImpl getSimpleMediaPlayer() {
        return simpleMediaPlayer;
    }

    public abstract void onPrepared(SimpleMediaPlayerImpl simpleMediaPlayer);

    public abstract void perform();


    protected void submit(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        new Thread(runnable).start();
    }

    protected void post(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        handler.post(runnable);
    }

    //目前状态下的音视频长度
    public abstract int getDuration();

    //得到当前的音频长度
    public abstract int getCurrentPosition();
}
