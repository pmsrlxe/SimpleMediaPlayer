package simple.media.player.action.preparing;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.OnPreparedListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PreparingPauseAction extends PreparingBaseAction {
    private OnPreparedListener onPreparedListener = new OnPreparedListener() {
        @Override
        public void onPrepared() {
            try {
                //不用pause，因为preparing完之后，需要start才能播放
                //这里直接改状态就行了
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Paused);
            } catch (Exception ex) {
                ex.printStackTrace();
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
            }
        }
    };

    public PreparingPauseAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
        mediaPlayer.addOnPreparedListener(onPreparedListener);
    }

    @Override
    public void onRelease() {
        super.onRelease();
        simpleMediaPlayer.removeOnPreparedListener(onPreparedListener);
    }
}
