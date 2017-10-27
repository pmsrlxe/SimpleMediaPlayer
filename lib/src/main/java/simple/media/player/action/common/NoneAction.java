package simple.media.player.action.common;


import android.util.Log;

import simple.media.player.action.BaseMediaPlayerAction;
import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.SimpleMediaPlayer;
import simple.media.player.player.sys.SysMediaPlayerImpl;

public class NoneAction extends BaseMediaPlayerAction {

    public NoneAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState changeToState) {
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

    }

    @Override
    public void perform() {
super.perform();
        Log.e("SimpleMediaPlayer", "perform none action from " + getFromState() + " to " + getChangeToState());
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }
}
