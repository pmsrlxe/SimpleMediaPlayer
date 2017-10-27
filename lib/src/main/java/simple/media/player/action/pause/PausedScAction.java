package simple.media.player.action.pause;


import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.player.SimpleMediaPlayer;

public class PausedScAction extends PauseBaseAction {

    public PausedScAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }


    @Override
    public boolean onInfo(SimpleMediaPlayer mediaPlayer, MediaPlayerInfo info) {
        return false;
    }

    @Override
    public boolean onError(SimpleMediaPlayer mediaPlayer, MediaPlayerError error) {
        getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Error);
        return false;
    }

    @Override
    public void onSeekComplete(SimpleMediaPlayer mediaPlayer) {
        getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Paused);
    }

    @Override
    public void onBufferingUpdate(SimpleMediaPlayer mediaPlayer, int percent) {

    }

    @Override
    public void onCompletion(SimpleMediaPlayer mediaPlayer) {

    }

    @Override
    public void perform() {
        super.perform();
        try {
            float pc = getSimpleMediaPlayer().getMediaParams().getSeekToPercent() * 1.0f / 100;
            int timeInSecond = (int) (pc * getRealMediaPlayer().doGetDurationMs());
            getRealMediaPlayer().doSeekTo(timeInSecond);
            getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Seeking);
        } catch (Throwable ex) {
            ex.printStackTrace();
            getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }
    }
}
