package mediaplayer.yxy.mediaplayer.action;

import android.util.Log;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public abstract class BaseMediaPlayerAction extends MediaPlayerAction {

    public BaseMediaPlayerAction(SimpleMediaPlayer simpleMediaPlayer, MediaPlayerState changeToState) {
        super(simpleMediaPlayer, changeToState);
    }

    @Override
    public void perform() {
        Log.i(SimpleMediaPlayer.TAG, "perform " + this.getClass().getSimpleName() + "->" + getChangeToState());
    }
}
