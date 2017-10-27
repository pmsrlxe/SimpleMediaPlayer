package simple.media.player.listener;


import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.player.SimpleMediaPlayer;

public interface MediaPlayerStateAware {

    boolean onInfo(SimpleMediaPlayer mediaPlayer, MediaPlayerInfo info);

    boolean onError(SimpleMediaPlayer mediaPlayer, MediaPlayerError error);

    void onSeekComplete(SimpleMediaPlayer mediaPlayer);

    void onBufferingUpdate(SimpleMediaPlayer mediaPlayer, int percent);

    void onCompletion(SimpleMediaPlayer mediaPlayer);

}
