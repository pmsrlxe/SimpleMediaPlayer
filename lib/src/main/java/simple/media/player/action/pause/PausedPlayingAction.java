package simple.media.player.action.pause;

import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.OnSeekCompleteListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PausedPlayingAction extends PauseBaseAction {
    private OnSeekCompleteListener onSeekCompleteListener = new OnSeekCompleteListener() {
        @Override
        public void onSeekComplete() {
            try {
                realMediaPlayer.doStart();
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Playing);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
            } finally {
                notifyActionFinish();
            }

        }
    };

    public PausedPlayingAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
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
            //要看看是不是pause在最后了，pause在最后，需要seek到0，继续放
            if (simpleMediaPlayer.getRuntimeInfo().getCurrentPositionInMs() ==
                    simpleMediaPlayer.getRuntimeInfo().getDurationInMs()) {
                realMediaPlayer.doSeekTo(0);
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Seeking);
            } else {
                realMediaPlayer.doStart();
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Playing);
                notifyActionFinish();
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }
    }
}
