package simple.media.player.action.playing;


import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.OnCompleteListener;
import simple.media.player.listener.OnSeekCompleteListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PlayingSeekingAction extends PlayingBaseAction {
    private OnSeekCompleteListener seekCompleteListener = new OnSeekCompleteListener() {
        @Override
        public void onSeekComplete() {
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Playing);
        }
    };
    private OnCompleteListener onCompleteListener = new OnCompleteListener() {
        @Override
        public void onComplete() {
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Paused);
        }
    };

    public PlayingSeekingAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
        mediaPlayer.addOnSeekCompleteListener(seekCompleteListener);
        mediaPlayer.addOnCompleteListener(onCompleteListener);
    }


    @Override
    public void perform() {
        super.perform();
        try {
            float pc = simpleMediaPlayer.getMediaParams().getSeekToPercent() * 1.0f / 100;
            int timeInMs = (int) (pc * realMediaPlayer.doGetDurationMs());
            realMediaPlayer.doSeekTo(timeInMs);
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Seeking);
        } catch (Throwable ex) {
            ex.printStackTrace();
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }

    }

    @Override
    public void onRelease() {
        super.onRelease();
        simpleMediaPlayer.removeOnSeekCompleteListener(seekCompleteListener);
        simpleMediaPlayer.removeOnCompleteListener(onCompleteListener);
    }
}

