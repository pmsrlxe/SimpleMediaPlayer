package simple.media.player.action.reset;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class ResetPauseAction extends ResetBaseAction {

    public ResetPauseAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer,changeToState);
    }
}
