package simple.media.player.action.reset;


import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.player.SimpleMediaPlayer;

public class ResetPlayingAction extends ResetBaseAction {

    public ResetPlayingAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }


    @Override
    public boolean onInfo(SimpleMediaPlayer mediaPlayer, MediaPlayerInfo info) {
        return false;
    }

    @Override
    public boolean onError(SimpleMediaPlayer mediaPlayer, MediaPlayerError error) {
        getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Error);
        return false;
    }

    @Override
    public void onSeekComplete(SimpleMediaPlayer mediaPlayer) {
        try {
            getRealMediaPlayer().doStart();
            getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Playing);
        } catch (Throwable throwable) {
            getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }
    }

    @Override
    public void onBufferingUpdate(SimpleMediaPlayer mediaPlayer, int percent) {

    }

    @Override
    public void onCompletion(SimpleMediaPlayer mediaPlayer) {

    }

    @Override
    public long getDurationMs() {
        if (!getSimpleMediaPlayer().getMediaPlayerState().isHasDataState()) {
            return 0;
        }
        return super.getDurationMs();
    }

    @Override
    public long getCurrentPositionMs() {
        if (!getSimpleMediaPlayer().getMediaPlayerState().isHasDataState()) {
            return 0;
        }
        return super.getCurrentPositionMs();
    }

    @Override
    public void perform() {
        super.perform();
        //已经是reset状态，需要开始播放，那么就preparing
        try {
            getRealMediaPlayer().doPrepareAsync(getSimpleMediaPlayer().getMediaParams());
            getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Preparing);
        } catch (Throwable ex) {
            getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }
    }

    private void onPrepared(SimpleMediaPlayer simpleMediaPlayer) {
        try {
            //0-100
            int percentInt = getSimpleMediaPlayer().getMediaParams().getSeekToPercent();
            int seekToMs = getSimpleMediaPlayer().getMediaParams().getSeekToMs();

            int resultSeekMs = 0;
            if (percentInt > 0) {
                resultSeekMs = (int) (percentInt * 1.0f / 100 * getDurationMs());
            } else if (seekToMs > 0) {
                resultSeekMs = seekToMs;
            }
            //处理整数的50000或者60000这种，加上100好像没有关键帧问题了
            //如:给了50000，华为p10会播放的时候回跳到40000，加上100，就没事了
            resultSeekMs += 100;

            //是否有跳转
            if (resultSeekMs > 0) {
                getRealMediaPlayer().doSeekTo(resultSeekMs);
                getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Seeking);
            } else {
                getRealMediaPlayer().doStart();
                getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Playing);
            }

        } catch (Throwable ex) {
            ex.printStackTrace();
            getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }
    }
}
