package mediaplayer.yxy.mediaplayer.action;

import mediaplayer.yxy.mediaplayer.MediaPlayerStateAware;
import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public abstract class MediaPlayerAction implements MediaPlayerStateAware {
    private final SimpleMediaPlayer mediaPlayer;
    private final MediaPlayerState wantState;

    public MediaPlayerAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState wantState) {
        this.mediaPlayer = mediaPlayer;
        this.wantState = wantState;
    }

    public MediaPlayerState getWantState() {
        return wantState;
    }

    public SimpleMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public abstract void perform();
}
