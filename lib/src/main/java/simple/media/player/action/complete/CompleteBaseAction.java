package simple.media.player.action.complete;


import simple.media.player.action.BaseMediaPlayerAction;
import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.player.SimpleMediaPlayer;

public class CompleteBaseAction extends BaseMediaPlayerAction {

    public CompleteBaseAction(SimpleMediaPlayer simpleMediaPlayer, MediaPlayerState changeToState) {
        super(simpleMediaPlayer, changeToState);
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
    public long getDurationMs() {
        try {
            return getRealMediaPlayer().doGetDurationMs();
        } catch (Throwable throwable) {
            return 0;
        }
    }

    @Override
    public long getCurrentPositionMs() {
        try {
            return getRealMediaPlayer().doGetCurrentPositionMs();
        } catch (Throwable throwable) {
            return 0;
        }
    }
}
