package simple.media.player.listener;


import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerInfo;
import simple.media.player.media.SimpleMediaPlayerImpl;

public interface MediaPlayerStateAware {

    boolean onInfo(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerInfo info);

    boolean onError(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerError error);

    void onSeekComplete(SimpleMediaPlayerImpl mediaPlayer);

    void onBufferingUpdate(SimpleMediaPlayerImpl mediaPlayer, int percent);

    void onCompletion(SimpleMediaPlayerImpl mediaPlayer);

}
