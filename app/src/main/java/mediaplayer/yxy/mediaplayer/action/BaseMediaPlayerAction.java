package mediaplayer.yxy.mediaplayer.action;

import android.util.Log;

import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;

public abstract class BaseMediaPlayerAction extends MediaPlayerAction {

    public BaseMediaPlayerAction(SimpleMediaPlayerImpl simpleMediaPlayer, MediaPlayerState changeToState) {
        super(simpleMediaPlayer, changeToState);
    }

    @Override
    public void perform() {
        Log.i(SimpleMediaPlayer.TAG, "perform " + this.getClass().getSimpleName() + "->" + getChangeToState());
    }
}
