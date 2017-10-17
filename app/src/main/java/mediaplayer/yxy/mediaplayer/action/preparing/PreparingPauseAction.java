package mediaplayer.yxy.mediaplayer.action.preparing;

import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class PreparingPauseAction extends PreparingBaseAction {

    public PreparingPauseAction(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }

    @Override
    public void onPrepared(SimpleMediaPlayerImpl simpleMediaPlayer) {
        try {
            //不用pause，因为preparing完之后，需要start才能播放
            //这里直接改状态就行了
            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Paused);
        } catch (Exception ex) {
            ex.printStackTrace();
            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Error);
        }
    }

    @Override
    public boolean onInfo(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerInfo info) {
        return false;
    }

    @Override
    public boolean onError(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerError error) {
        return false;
    }

    @Override
    public void onSeekComplete(SimpleMediaPlayerImpl mediaPlayer) {

    }

    @Override
    public void onBufferingUpdate(SimpleMediaPlayerImpl mediaPlayer, int percent) {

    }

    @Override
    public void onCompletion(SimpleMediaPlayerImpl mediaPlayer) {

    }

    @Override
    public void perform() {
        super.perform();

    }
}
