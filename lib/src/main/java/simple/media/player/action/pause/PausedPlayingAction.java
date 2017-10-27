package simple.media.player.action.pause;

import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.player.SimpleMediaPlayer;

public class PausedPlayingAction extends PauseBaseAction {

    public PausedPlayingAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }


    @Override
    public boolean onInfo(SimpleMediaPlayer mediaPlayer, MediaPlayerInfo info) {
        return false;
    }

    @Override
    public boolean onError(SimpleMediaPlayer mediaPlayer, MediaPlayerError error) {
        return false;
    }

    @Override
    public void onSeekComplete(SimpleMediaPlayer mediaPlayer) {

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
            getRealMediaPlayer().doStart();
            getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Playing);
        } catch (Throwable ex) {
            ex.printStackTrace();
            getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }
    }
}
