package mediaplayer.yxy.mediaplayer.action.reset;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class ResetPlayingAction extends ResetBaseAction {

    public ResetPlayingAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }

    @Override
    public void onPrepared(SimpleMediaPlayer simpleMediaPlayer) {
        try {
            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Prepared);
            getRealMediaPlayer().start();
            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Playing);

            //0-100
            int percent = getSimpleMediaPlayer().getMediaParams().getSeekToPercent();
            int seekSecond = getSimpleMediaPlayer().getMediaParams().getSeekToSecond();

            int resultSeekSecond = 0;
            if (percent > 0) {
                resultSeekSecond = (int) (percent * 1.0f / 100 * getDuration());
            } else if (seekSecond > 0) {
                resultSeekSecond = seekSecond;
            }
            //是否有跳转
            if (resultSeekSecond > 0) {
                getRealMediaPlayer().seekTo(resultSeekSecond * 1000);
            }
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
    public int getDuration() {
        if (getSimpleMediaPlayer().getMediaPlayerState() != MediaPlayerState.Prepared
                || getSimpleMediaPlayer().getMediaPlayerState() != MediaPlayerState.Playing) {
            return 0;
        }
        return super.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        if (getSimpleMediaPlayer().getMediaPlayerState() != MediaPlayerState.Prepared
                || getSimpleMediaPlayer().getMediaPlayerState() != MediaPlayerState.Playing) {
            return 0;
        }
        return super.getCurrentPosition();
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
