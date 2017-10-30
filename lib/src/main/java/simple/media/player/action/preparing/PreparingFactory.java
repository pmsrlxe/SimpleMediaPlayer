package simple.media.player.action.preparing;


import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.common.CommonReleaseAction;
import simple.media.player.action.common.NoneAction;
import simple.media.player.action.reset.ResetPlayingAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PreparingFactory {

    private PreparingFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Reset:    //正在准备，突然要你reset
                return new PreparingResetAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Paused:   //意思是，prepared后，要pause
                return new PreparingPauseAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Playing:  //prepared后要start
                return new ResetPlayingAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Stopped:  //好不容易prepared了，又要stop
                return new PreparingStopAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Preparing:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Prepared: //理应发生
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Error:    //准备但是出错了
                return new PreparingErrorAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Complete: //准备怎么可能导致播放完毕
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case PlayBuffering://准备不会buffer
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Seeking:  //准备完毕，需要seek到某个地方
                return new PreparingSeekingAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case SeekComplete: //临时状态没有啥要干的
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
