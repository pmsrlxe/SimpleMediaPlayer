package simple.media.player.action;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

/**
 * 不同状态下的media具体行为
 */
public abstract class MediaPlayerAction {
    protected final SimpleMediaPlayer simpleMediaPlayer;
    protected final MediaPlayerState changeToState;
    protected final MediaPlayerState fromState;
    protected final RealMediaPlayer realMediaPlayer;
    protected ActionListener actionListener;

    public MediaPlayerAction(SimpleMediaPlayer simpleMediaPlayer,
                             RealMediaPlayer realMediaPlayer,
                             MediaPlayerState changeToState) {
        this.simpleMediaPlayer = simpleMediaPlayer;
        this.realMediaPlayer = realMediaPlayer;
        this.fromState = simpleMediaPlayer.getMediaPlayerState();
        this.changeToState = changeToState;
    }

    public void notifyActionFinish() {
        if (actionListener != null) {
            actionListener.onActionFinish();
        }
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    /**
     * 执行当前action
     */
    public abstract void perform();


    /**
     * 切换action的时候会释放上一个
     */
    public void onRelease() {

    }
}
