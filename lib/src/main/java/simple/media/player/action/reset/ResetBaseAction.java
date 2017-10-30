package simple.media.player.action.reset;


import simple.media.player.action.BaseMediaPlayerAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class ResetBaseAction extends BaseMediaPlayerAction {

    public ResetBaseAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(simpleMediaPlayer, realMediaPlayer, changeToState);
    }
}
