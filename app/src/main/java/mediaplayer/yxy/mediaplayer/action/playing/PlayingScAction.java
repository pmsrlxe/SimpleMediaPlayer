package mediaplayer.yxy.mediaplayer.action.playing;

import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class PlayingScAction extends PlayingBaseAction {

    public PlayingScAction(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerState changeToState) {
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
        getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Playing);
    }

    @Override
    public void onBufferingUpdate(SimpleMediaPlayerImpl mediaPlayer, int percent) {

    }

    @Override
    public void onCompletion(SimpleMediaPlayerImpl mediaPlayer) {
        getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Paused);
    }

    @Override
    public void perform() {
        super.perform();
        try {
            float pc = getSimpleMediaPlayer().getMediaParams().getSeekToPercent() * 1.0f / 100;
            int timeInSecond = (int) (pc * getRealMediaPlayer().getDuration());
            getRealMediaPlayer().seekTo(timeInSecond);
            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Seeking);
        } catch (Exception ex) {
            ex.printStackTrace();
            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Error);
        }

    }
}
