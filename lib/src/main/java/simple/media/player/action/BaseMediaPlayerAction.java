package simple.media.player.action;


import android.util.Log;

import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public abstract class BaseMediaPlayerAction extends MediaPlayerAction {

    public BaseMediaPlayerAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(simpleMediaPlayer, realMediaPlayer, changeToState);
    }

    @Override
    public void perform() {
        Log.i(SimpleMediaPlayer.TAG, "perform " + this.getClass().getSimpleName() + "->" + changeToState);
    }

    @Override
    public void onRelease() {
        super.onRelease();
    }
}
