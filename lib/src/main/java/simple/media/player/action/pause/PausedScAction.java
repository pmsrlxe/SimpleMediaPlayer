package simple.media.player.action.pause;


import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.OnErrorListener;
import simple.media.player.listener.OnSeekCompleteListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PausedScAction extends PauseBaseAction {
    private OnErrorListener onErrorListener = new OnErrorListener() {
        @Override
        public void onError(MediaPlayerError error) {
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }
    };
    private OnSeekCompleteListener onSeekCompleteListener = new OnSeekCompleteListener() {
        @Override
        public void onSeekComplete() {
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Paused);
        }
    };

    public PausedScAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }


    @Override
    public void perform() {
        super.perform();
        try {
            float pc = simpleMediaPlayer.getMediaParams().getSeekToPercent() * 1.0f / 100;
            int timeInSecond = (int) (pc * realMediaPlayer.doGetDurationMs());
            realMediaPlayer.doSeekTo(timeInSecond);
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Seeking);
        } catch (Throwable ex) {
            ex.printStackTrace();
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }
    }

    @Override
    public void onRelease() {
        super.onRelease();
        simpleMediaPlayer.addOnErrorListener(onErrorListener);
        simpleMediaPlayer.addOnSeekCompleteListener(onSeekCompleteListener);
    }
}
