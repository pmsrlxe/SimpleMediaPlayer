package simple.media.player.action.playing;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.sys.SysMediaPlayerImpl;

public class PlayingReleaseAction extends PlayingBaseAction {

    public PlayingReleaseAction(SysMediaPlayerImpl mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }


}
