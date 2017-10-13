package mediaplayer.yxy.mediaplayer.action;

import android.media.MediaPlayer;

import mediaplayer.yxy.mediaplayer.MediaPlayerStateAware;
import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public abstract class MediaPlayerAction implements MediaPlayerStateAware {
    private final SimpleMediaPlayer simpleMediaPlayer;
    private final MediaPlayerState changeToState;
    private final MediaPlayerState fromState;
    private final MediaPlayer realMediaPlayer;

    public MediaPlayerAction(SimpleMediaPlayer simpleMediaPlayer, MediaPlayerState changeToState) {
        this.simpleMediaPlayer = simpleMediaPlayer;
        this.realMediaPlayer = simpleMediaPlayer.getRealMediaPlayer();
        this.fromState = simpleMediaPlayer.getMediaPlayerState();
        this.changeToState = changeToState;
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
        new Thread(runnable).start();
    }
}
