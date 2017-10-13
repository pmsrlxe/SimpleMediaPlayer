package mediaplayer.yxy.mediaplayer.action.pause;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.common.CommonReleaseAction;
import mediaplayer.yxy.mediaplayer.action.common.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class PauseFactory {

    private PauseFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer simpleMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Reset:    //可以重新reset
                return new PausedResetAction(simpleMediaPlayer, changeToState);
            case Paused:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Started:  //重新播放
                return new PausedStartedAction(simpleMediaPlayer, changeToState);
            case Stopped:  //pause可以停止
                return new PausedStopedAction(simpleMediaPlayer, changeToState);
            case Preparing://已经preparing了
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Prepared: //已经prepared了
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, changeToState);
            case Error:    //pause还是可能发生异常的
                return new PausedErrorAction(simpleMediaPlayer, changeToState);
            case Complete:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Buffering:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Seeking:  //暂停是可以seek的
                return new PausedSeekingAction(simpleMediaPlayer, changeToState);
            case SeekComplete:
                return new PausedCompleteAction(simpleMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }

    }

}
