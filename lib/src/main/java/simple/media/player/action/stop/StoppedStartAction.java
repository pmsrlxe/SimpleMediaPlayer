package simple.media.player.action.stop;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class StoppedStartAction extends StopBaseAction {

    public StoppedStartAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }

}
