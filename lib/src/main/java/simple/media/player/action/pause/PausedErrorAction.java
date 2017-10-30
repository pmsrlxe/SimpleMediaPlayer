package simple.media.player.action.pause;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PausedErrorAction extends PauseBaseAction {

    public PausedErrorAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }
}
