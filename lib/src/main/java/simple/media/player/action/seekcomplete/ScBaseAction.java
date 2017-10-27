package simple.media.player.action.seekcomplete;


import simple.media.player.action.BaseMediaPlayerAction;
import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.player.SimpleMediaPlayer;
import simple.media.player.player.sys.SysMediaPlayerImpl;

public class ScBaseAction extends BaseMediaPlayerAction {

    public ScBaseAction(SimpleMediaPlayer simpleMediaPlayer, MediaPlayerState changeToState) {
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
    public void onPrepared(SysMediaPlayerImpl simpleMediaPlayer) {

    }

    @Override
    public int getDuration() {
        try {
            return getRealMediaPlayer().getDuration();
        } catch (Throwable throwable) {
            return 0;
        }
    }

    @Override
    public int getCurrentPosition() {
        try {
            return getRealMediaPlayer().getCurrentPosition();
        } catch (Throwable throwable) {
            return 0;
        }
    }
}
