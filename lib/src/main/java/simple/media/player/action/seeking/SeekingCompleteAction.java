package simple.media.player.action.seeking;


import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.player.SimpleMediaPlayer;
import simple.media.player.player.sys.SysMediaPlayerImpl;

public class SeekingCompleteAction extends SeekingBaseAction {

    public SeekingCompleteAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }

    @Override
    public void onPrepared(SysMediaPlayerImpl simpleMediaPlayer) {

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
        getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Paused);
    }

    @Override
    public void perform() {
        super.perform();
    }
}
