package simple.media.player.action.buffering;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.sys.SysMediaPlayerImpl;

public class BufferingStartAction extends BufferingBaseAction {

    public BufferingStartAction(SysMediaPlayerImpl mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }

}
