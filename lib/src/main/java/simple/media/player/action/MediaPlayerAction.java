package simple.media.player.action;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public abstract class MediaPlayerAction {
    protected final SimpleMediaPlayer simpleMediaPlayer;
    protected final MediaPlayerState changeToState;
    protected final MediaPlayerState fromState;
    protected final RealMediaPlayer realMediaPlayer;

    public MediaPlayerAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        this.simpleMediaPlayer = simpleMediaPlayer;
        this.realMediaPlayer = realMediaPlayer;
        this.fromState = simpleMediaPlayer.getMediaPlayerState();
        this.changeToState = changeToState;
    }

    public abstract void perform();


    //切换action的时候回释放上一个
    public void onRelease() {

    }
}
