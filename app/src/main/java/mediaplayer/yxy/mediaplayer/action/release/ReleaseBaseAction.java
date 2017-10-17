package mediaplayer.yxy.mediaplayer.action.release;

import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;
import mediaplayer.yxy.mediaplayer.action.BaseMediaPlayerAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class ReleaseBaseAction extends BaseMediaPlayerAction {

    public ReleaseBaseAction(SimpleMediaPlayerImpl simpleMediaPlayer, MediaPlayerState changeToState) {
        super(simpleMediaPlayer, changeToState);
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
    public void onPrepared(SimpleMediaPlayerImpl simpleMediaPlayer) {

    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }
}
