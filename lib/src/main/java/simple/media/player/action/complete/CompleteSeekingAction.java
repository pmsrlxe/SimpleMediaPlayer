package simple.media.player.action.complete;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.OnSeekCompleteListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class CompleteSeekingAction extends CompleteBaseAction {
    private OnSeekCompleteListener onSeekCompleteListener = new OnSeekCompleteListener() {
        @Override
        public void onSeekComplete() {
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Paused);
            notifyActionFinish();
        }
    };

    public CompleteSeekingAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
        simpleMediaPlayer.addOnSeekCompleteListener(onSeekCompleteListener);
    }

    @Override
    public void onRelease() {
        super.onRelease();
        simpleMediaPlayer.removeOnSeekCompleteListener(onSeekCompleteListener);
    }

    @Override
    public void perform() {
        super.perform();
        try {
            realMediaPlayer.doSeekTo(simpleMediaPlayer.getMediaParams().getSeekToMs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }
    }
}
