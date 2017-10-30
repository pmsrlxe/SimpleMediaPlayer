package simple.media.player.action.playing;

import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.OnCompleteListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PlayingCompleteAction extends PlayingBaseAction {
    private OnCompleteListener onCompleteListener = new OnCompleteListener() {
        @Override
        public void onComplete() {
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Paused);
        }
    };

    public PlayingCompleteAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
        simpleMediaPlayer.addOnCompleteListener(onCompleteListener);
    }


    @Override
    public void onRelease() {
        super.onRelease();
        simpleMediaPlayer.removeOnCompleteListener(onCompleteListener);
    }
}
