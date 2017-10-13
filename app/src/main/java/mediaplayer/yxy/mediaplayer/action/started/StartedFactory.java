package mediaplayer.yxy.mediaplayer.action.started;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.common.CommonReleaseAction;
import mediaplayer.yxy.mediaplayer.action.common.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class StartedFactory {

    private StartedFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(wrapper, changeToState);
            case Reset:
                return new StartedResetAction(wrapper, changeToState);
            case Paused:
                return new StartedPauseAction(wrapper, changeToState);
            case Started:
                return new NoneAction(wrapper, changeToState);
            case Stopped:
                return new StartedStopAction(wrapper, changeToState);
            case Preparing:
                return new NoneAction(wrapper, changeToState);
            case Prepared:
                return new NoneAction(wrapper, changeToState);
            case Released:
                return new CommonReleaseAction(wrapper, changeToState);
            case Error:    //start还是有可能发生异常的
                return new StartedErrorAction(wrapper, changeToState);
            case Complete: //播放中，有可能完成的
                return new StartedCompleteAction(wrapper, changeToState);
            case Buffering:
                return new NoneAction(wrapper, changeToState);
            case Seeking:
                return new StartedSeekingAction(wrapper, changeToState);
            case SeekComplete: //因为播放中，seek完成自动会播放
                return new NoneAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
