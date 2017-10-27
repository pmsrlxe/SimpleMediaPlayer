package simple.media.player.action;


import android.util.Log;

import simple.media.player.data.MediaPlayerState;
import simple.media.player.media.SimpleMediaPlayer;
import simple.media.player.media.SimpleMediaPlayerImpl;

public abstract class BaseMediaPlayerAction extends MediaPlayerAction {

    public BaseMediaPlayerAction(SimpleMediaPlayerImpl simpleMediaPlayer, MediaPlayerState changeToState) {
        super(simpleMediaPlayer, changeToState);
    }

    @Override
    public void perform() {
        Log.i(SimpleMediaPlayer.TAG, "perform " + this.getClass().getSimpleName() + "->" + getChangeToState());
    }
}
