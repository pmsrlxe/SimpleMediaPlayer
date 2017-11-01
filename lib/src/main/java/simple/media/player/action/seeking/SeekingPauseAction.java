package simple.media.player.action.seeking;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.OnSeekCompleteListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class SeekingPauseAction extends SeekingBaseAction {
    private OnSeekCompleteListener onSeekCompleteListener = new OnSeekCompleteListener() {
        @Override
        public void onSeekComplete() {
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

    public SeekingPauseAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
        simpleMediaPlayer.addOnSeekCompleteListener(onSeekCompleteListener);
    }

    @Override
    public void onRelease() {
        super.onRelease();
        simpleMediaPlayer.removeOnSeekCompleteListener(onSeekCompleteListener);
    }
}
