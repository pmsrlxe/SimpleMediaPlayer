package mediaplayer.yxy.mediaplayer;

import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;

public interface MediaPlayerStateAware {

    boolean onInfo(SimpleMediaPlayer mediaPlayer, MediaPlayerInfo info);

    boolean onError(SimpleMediaPlayer mediaPlayer, MediaPlayerError error);

    void onSeekComplete(SimpleMediaPlayer mediaPlayer);

    void onBufferingUpdate(SimpleMediaPlayer mediaPlayer, int percent);

    void onCompletion(SimpleMediaPlayer mediaPlayer);

}
