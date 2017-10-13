package mediaplayer.yxy.mediaplayer.action;

import mediaplayer.yxy.mediaplayer.MediaPlayerStateAware;
import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public abstract class MediaPlayerAction implements MediaPlayerStateAware {
    private final SimpleMediaPlayer mediaPlayer;
    private final MediaPlayerState changeToState;
    private final MediaPlayerState fromState;

    public MediaPlayerAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState changeToState) {
        this.mediaPlayer = mediaPlayer;
        this.fromState = mediaPlayer.getMediaPlayerState();
        this.changeToState = changeToState;
    }

    public MediaPlayerState getFromState() {
        return fromState;
    }

    public MediaPlayerState getchangeToState() {
        return changeToState;
    }

    public SimpleMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public abstract void perform();
}
