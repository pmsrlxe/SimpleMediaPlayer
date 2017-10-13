package mediaplayer.yxy.mediaplayer.action.reset;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.BaseMediaPlayerAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class ResetPlayingAction extends BaseMediaPlayerAction {

    public ResetPlayingAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }

    @Override
    public void onPrepared(SimpleMediaPlayer simpleMediaPlayer) {
        try {
            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Prepared);
            getRealMediaPlayer().start();
            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Playing);
        } catch (Exception ex) {
            ex.printStackTrace();
            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Error);
        }
    }

    @Override
    public boolean onInfo(SimpleMediaPlayer mediaPlayer, MediaPlayerInfo info) {
        return false;
    }

    @Override
    public boolean onError(SimpleMediaPlayer mediaPlayer, MediaPlayerError error) {
        getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Error);
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
        //已经是reset状态，需要开始播放，那么就preparing
        try {
            getRealMediaPlayer().prepareAsync();
            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Preparing);
        } catch (Exception ex) {
            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Error);
        }
    }
}
