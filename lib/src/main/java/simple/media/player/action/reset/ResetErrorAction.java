package simple.media.player.action.reset;


import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.player.SimpleMediaPlayer;

public class ResetErrorAction extends ResetBaseAction {

    public ResetErrorAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }


    @Override
    public boolean onInfo(SimpleMediaPlayer mediaPlayer, MediaPlayerInfo info) {
        return false;
    }

    @Override
    public boolean onError(SimpleMediaPlayer mediaPlayer, MediaPlayerError error) {
        return false;
    }

    @Override
    public void onSeekComplete(SimpleMediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingUpdate(SimpleMediaPlayer mediaPlayer, int percent) {

    }

    @Override
    public void onCompletion(SimpleMediaPlayer mediaPlayer) {

    }

    @Override
    public void perform() {
        super.perform();
    }

    @Override
    public long getCurrentPositionMs() {
        return 0;
    }

    @Override
    public long getDurationMs() {
        return 0;
    }
}
