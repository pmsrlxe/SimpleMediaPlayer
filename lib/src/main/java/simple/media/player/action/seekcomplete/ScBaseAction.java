package simple.media.player.action.seekcomplete;


import simple.media.player.action.BaseMediaPlayerAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class ScBaseAction extends BaseMediaPlayerAction {

    public ScBaseAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(simpleMediaPlayer, realMediaPlayer, changeToState);
    }


}
