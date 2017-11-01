package simple.media.player.action.prepared;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PreparedSeekingAction extends PreparedBaseAction {

    public PreparedSeekingAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }


}
