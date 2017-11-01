package simple.media.player.action.seeking;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.OnCompleteListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class SeekingCompleteAction extends SeekingBaseAction {
    private OnCompleteListener onCompleteListener = new OnCompleteListener() {
        @Override
        public void onComplete() {
            try {
                realMediaPlayer.doPause();
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Paused);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
            } finally {
                notifyActionFinish();
            }
        }
    };

    public SeekingCompleteAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
        mediaPlayer.addOnCompleteListener(onCompleteListener);
    }

    @Override
    public void onRelease() {
        super.onRelease();
        simpleMediaPlayer.removeOnCompleteListener(onCompleteListener);
    }
}
