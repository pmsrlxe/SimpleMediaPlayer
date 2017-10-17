package mediaplayer.yxy.mediaplayer.listener;

import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;

public interface MediaPlayerStateAware {

    boolean onInfo(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerInfo info);

    boolean onError(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerError error);

    void onSeekComplete(SimpleMediaPlayerImpl mediaPlayer);

    void onBufferingUpdate(SimpleMediaPlayerImpl mediaPlayer, int percent);

    void onCompletion(SimpleMediaPlayerImpl mediaPlayer);

}
