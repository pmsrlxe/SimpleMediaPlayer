package mediaplayer.yxy.mediaplayer.action.pause;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.common.CommonReleaseAction;
import mediaplayer.yxy.mediaplayer.action.common.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class PauseFactory {

    private PauseFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(wrapper, changeToState);
            case Reset:    //可以重新reset
                return new PausedResetAction(wrapper, changeToState);
            case Paused:
                return new NoneAction(wrapper, changeToState);
            case Started:  //重新播放
                return new PausedStartedAction(wrapper, changeToState);
            case Stopped:  //pause可以停止
                return new PausedStopedAction(wrapper, changeToState);
            case Preparing://已经preparing了
                return new NoneAction(wrapper, changeToState);
            case Prepared: //已经prepared了
                return new NoneAction(wrapper, changeToState);
            case Released:
                return new CommonReleaseAction(wrapper, changeToState);
            case Error:    //pause还是可能发生异常的
                return new PausedErrorAction(wrapper, changeToState);
            case Complete:
                return new NoneAction(wrapper, changeToState);
            case Buffering:
                return new NoneAction(wrapper, changeToState);
            case Seeking:  //暂停是可以seek的
                return new PausedSeekingAction(wrapper, changeToState);
            case SeekComplete:
                return new PausedCompleteAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }

    }

}
