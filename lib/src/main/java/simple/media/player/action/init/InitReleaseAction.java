package simple.media.player.action.init;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.sys.SysMediaPlayerImpl;

public class InitReleaseAction extends InitBaseAction {

    public InitReleaseAction(SysMediaPlayerImpl mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }

}
