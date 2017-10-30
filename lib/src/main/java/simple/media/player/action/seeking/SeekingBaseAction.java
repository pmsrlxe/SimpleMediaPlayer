package simple.media.player.action.seeking;


import simple.media.player.action.BaseMediaPlayerAction;
import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class SeekingBaseAction extends BaseMediaPlayerAction {

    public SeekingBaseAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(simpleMediaPlayer, realMediaPlayer, changeToState);
    }

}
