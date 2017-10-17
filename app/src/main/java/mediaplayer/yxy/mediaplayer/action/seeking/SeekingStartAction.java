package mediaplayer.yxy.mediaplayer.action.seeking;

import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class SeekingStartAction extends SeekingBaseAction {

    public SeekingStartAction(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }

    @Override
    public void onPrepared(SimpleMediaPlayerImpl simpleMediaPlayer) {

    }

    @Override
    public boolean onInfo(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerInfo info) {
        return false;
    }

    @Override
    public boolean onError(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerError error) {
        return false;
    }

    @Override
    public void onSeekComplete(SimpleMediaPlayerImpl mediaPlayer) {

    }

    @Override
    public void onBufferingUpdate(SimpleMediaPlayerImpl mediaPlayer, int percent) {

    }

    @Override
    public void onCompletion(SimpleMediaPlayerImpl mediaPlayer) {

    }

    @Override
    public void perform() {
super.perform();

    }
}
