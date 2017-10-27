package simple.media.player.action.reset;


import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerInfo;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.media.SimpleMediaPlayerImpl;

public class ResetPlayingAction extends ResetBaseAction {

    public ResetPlayingAction(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }

    @Override
    public void onPrepared(SimpleMediaPlayerImpl simpleMediaPlayer) {
        try {
            //0-100
            int percentInt = getSimpleMediaPlayer().getMediaParams().getSeekToPercent();
            int seekToMs = getSimpleMediaPlayer().getMediaParams().getSeekToMs();

            int resultSeekMs = 0;
            if (percentInt > 0) {
                resultSeekMs = (int) (percentInt * 1.0f / 100 * getDuration());
            } else if (seekToMs > 0) {
                resultSeekMs = seekToMs;
            }
            //处理整数的50000或者60000这种，加上100好像没有关键帧问题了
            //如:给了50000，华为p10会播放的时候回跳到40000，加上100，就没事了
            resultSeekMs += 100;

            //是否有跳转
            if (resultSeekMs > 0) {
                getRealMediaPlayer().seekTo(resultSeekMs);
                getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Seeking);
            } else {
                getRealMediaPlayer().start();
                getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Playing);
            }

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
        getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Error);
        return false;
    }

    @Override
    public void onSeekComplete(SimpleMediaPlayerImpl mediaPlayer) {
        getRealMediaPlayer().start();
        getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Playing);
    }

    @Override
    public void onBufferingUpdate(SimpleMediaPlayerImpl mediaPlayer, int percent) {

    }

    @Override
    public void onCompletion(SimpleMediaPlayerImpl mediaPlayer) {

    }

    @Override
    public int getDuration() {
        if (!getSimpleMediaPlayer().getMediaPlayerState().isHasDataState()) {
            return 0;
        }
        return super.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        if (!getSimpleMediaPlayer().getMediaPlayerState().isHasDataState()) {
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
