package simple.media.player.action.buffering;


import simple.media.player.action.BaseMediaPlayerAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class BufferingBaseAction extends BaseMediaPlayerAction {

    public BufferingBaseAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(simpleMediaPlayer, realMediaPlayer, changeToState);
    }

}
