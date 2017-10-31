package simple.media.player.action.reset;


import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.OnErrorListener;
import simple.media.player.listener.OnPreparedListener;
import simple.media.player.listener.OnSeekCompleteListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class ResetPlayingAction extends ResetBaseAction {
    private static final int GAP = 100;
    private OnPreparedListener onPreparedListener = new OnPreparedListener() {
        @Override
        public void onPrepared() {
            simpleMediaPlayer.removeOnPreparedListener(this);
            doPrepared();
        }
    };
    private OnSeekCompleteListener onSeekCompleteListener = new OnSeekCompleteListener() {
        @Override
        public void onSeekComplete() {
            try {
                realMediaPlayer.doStart();
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Playing);
                notifyActionFinish();
            } catch (Throwable throwable) {
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
                notifyActionFinish();
            }
        }
    };

    private OnErrorListener onErrorListener = new OnErrorListener() {
        @Override
        public void onError(MediaPlayerError error) {
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
            notifyActionFinish();
        }
    };

    public ResetPlayingAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
        mediaPlayer.addOnSeekCompleteListener(onSeekCompleteListener);
        mediaPlayer.addOnErrorListener(onErrorListener);
    }

    @Override
    public void onRelease() {
        super.onRelease();
        simpleMediaPlayer.removeOnSeekCompleteListener(onSeekCompleteListener);
        simpleMediaPlayer.removeOnErrorListener(onErrorListener);
    }


    @Override
    public void perform() {
        super.perform();
        //已经是reset状态，需要开始播放，那么就preparing
        try {
            simpleMediaPlayer.addOnPreparedListener(onPreparedListener);

            realMediaPlayer.doPrepareAsync(simpleMediaPlayer.getMediaParams());
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Preparing);
        } catch (Throwable ex) {
            simpleMediaPlayer.removeOnPreparedListener(onPreparedListener);
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
            notifyActionFinish();
        }
    }

    private void doPrepared() {
        try {
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Prepared);
            //0-100
            int percentInt = simpleMediaPlayer.getMediaParams().getSeekToPercent();
            int seekToMs = simpleMediaPlayer.getMediaParams().getSeekToMs();

            int resultSeekMs = 0;
            if (percentInt > 0) {
                resultSeekMs = (int) (percentInt * 1.0f / 100 * simpleMediaPlayer.getRuntimeInfo().getDurationInMs());
            } else if (seekToMs > 0) {
                resultSeekMs = seekToMs;
            }
            //处理整数的50000或者60000这种，加上100好像没有关键帧问题了
            //如:给了50000，华为p10会播放的时候回跳到40000，加上100，就没事了
            resultSeekMs += GAP;

            //是否有跳转
            if (resultSeekMs - GAP > 0) {
                realMediaPlayer.doSeekTo(resultSeekMs);
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Seeking);
            } else {
                realMediaPlayer.doStart();
                simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Playing);
            }

        } catch (Throwable ex) {
            ex.printStackTrace();
            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Error);
        }finally {
            notifyActionFinish();
        }
    }
}
