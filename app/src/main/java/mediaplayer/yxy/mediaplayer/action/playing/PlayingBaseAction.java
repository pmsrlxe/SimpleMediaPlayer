package mediaplayer.yxy.mediaplayer.action.playing;

import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;
import mediaplayer.yxy.mediaplayer.action.BaseMediaPlayerAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class PlayingBaseAction extends BaseMediaPlayerAction {

    public PlayingBaseAction(SimpleMediaPlayerImpl simpleMediaPlayer, MediaPlayerState changeToState) {
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
