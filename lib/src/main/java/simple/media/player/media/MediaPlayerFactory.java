package simple.media.player.media;

public final class MediaPlayerFactory {

    private MediaPlayerFactory() {

    }

    public static SimpleMediaPlayer getMediaPlayer() {
        return new SimpleMediaPlayerImpl();
    }
}
