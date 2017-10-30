package simple.media.player.action.pause;


import simple.media.player.action.BaseMediaPlayerAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PauseBaseAction extends BaseMediaPlayerAction {

    public PauseBaseAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(simpleMediaPlayer, realMediaPlayer, changeToState);
    }
}
