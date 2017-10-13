package mediaplayer.yxy.mediaplayer.action.started;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.common.CommonReleaseAction;
import mediaplayer.yxy.mediaplayer.action.common.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class StartedFactory {

    private StartedFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer simpleMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Reset:
                return new StartedResetAction(simpleMediaPlayer, changeToState);
            case Paused:
                return new StartedPauseAction(simpleMediaPlayer, changeToState);
            case Started:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Stopped:
                return new StartedStopAction(simpleMediaPlayer, changeToState);
            case Preparing:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Prepared:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, changeToState);
            case Error:    //start还是有可能发生异常的
                return new StartedErrorAction(simpleMediaPlayer, changeToState);
            case Complete: //播放中，有可能完成的
                return new StartedCompleteAction(simpleMediaPlayer, changeToState);
            case Seeking:
                return new StartedScAction(simpleMediaPlayer, changeToState);
            case SeekComplete: //因为播放中，seek完成自动会播放
                return new StartedScAction(simpleMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
