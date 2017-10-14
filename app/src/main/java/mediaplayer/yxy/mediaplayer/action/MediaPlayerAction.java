package mediaplayer.yxy.mediaplayer.action;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;

import mediaplayer.yxy.mediaplayer.MediaPlayerStateAware;
import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public abstract class MediaPlayerAction implements MediaPlayerStateAware {
    private final SimpleMediaPlayer simpleMediaPlayer;
    private final MediaPlayerState changeToState;
    private final MediaPlayerState fromState;
    private final MediaPlayer realMediaPlayer;
    private final Handler handler;

    public MediaPlayerAction(SimpleMediaPlayer simpleMediaPlayer, MediaPlayerState changeToState) {
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

    public SimpleMediaPlayer getSimpleMediaPlayer() {
        return simpleMediaPlayer;
    }

    public abstract void onPrepared(SimpleMediaPlayer simpleMediaPlayer);

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
