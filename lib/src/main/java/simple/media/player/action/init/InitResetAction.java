package simple.media.player.action.init;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class InitResetAction extends InitBaseAction {

    public InitResetAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }

    @Override
    public void perform() {
        super.perform();
        try {
            realMediaPlayer.doReset();
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Reset);
        } catch (Throwable ex) {
            ex.printStackTrace();
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
        } finally {
            notifyActionFinish();
        }
    }
}
