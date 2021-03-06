package simple.media.player.action.seeking;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class SeekingErrorAction extends SeekingBaseAction {

    public SeekingErrorAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }

    @Override
    public void perform() {
        super.perform();
        simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
        notifyActionFinish();
    }
}
