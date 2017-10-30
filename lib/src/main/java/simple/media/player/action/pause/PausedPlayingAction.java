package simple.media.player.action.pause;

import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PausedPlayingAction extends PauseBaseAction {

    public PausedPlayingAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }

    @Override
    public void perform() {
        super.perform();
        try {
            realMediaPlayer.doStart();
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Playing);
        } catch (Throwable ex) {
            ex.printStackTrace();
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }
    }
}
