package simple.media.player.action.playing;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PlayingPauseAction extends PlayingBaseAction {

    public PlayingPauseAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }

    @Override
    public void perform() {
        super.perform();
        try {
            realMediaPlayer.doPause();
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Paused);
        } catch (Throwable ex) {
            ex.printStackTrace();
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
        } finally {
            notifyActionFinish();
        }

    }
}
