package mediaplayer.yxy.mediaplayer.action.reset;

import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;
import mediaplayer.yxy.mediaplayer.action.BaseMediaPlayerAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class ResetBaseAction extends BaseMediaPlayerAction {

    public ResetBaseAction(SimpleMediaPlayerImpl simpleMediaPlayer, MediaPlayerState changeToState) {
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
        return getRealMediaPlayer().getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return getRealMediaPlayer().getCurrentPosition();
    }
}
