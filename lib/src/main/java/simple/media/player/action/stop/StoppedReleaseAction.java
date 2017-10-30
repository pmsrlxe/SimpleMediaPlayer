package simple.media.player.action.stop;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.sys.SysMediaPlayerImpl;

public class StoppedReleaseAction extends StopBaseAction {

    public StoppedReleaseAction(SysMediaPlayerImpl mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }


}
