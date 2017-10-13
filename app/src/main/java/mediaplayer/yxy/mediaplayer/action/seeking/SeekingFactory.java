package mediaplayer.yxy.mediaplayer.action.seeking;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.common.CommonReleaseAction;
import mediaplayer.yxy.mediaplayer.action.common.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class SeekingFactory {

    private SeekingFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(wrapper, changeToState);
            case Reset:
                return new SeekingResetAction(wrapper, changeToState);
            case Paused:
                return new NoneAction(wrapper, changeToState);
            case Started:
                return new SeekingStartAction(wrapper, changeToState);
            case Stopped:
                return new SeekingStopAction(wrapper, changeToState);
            case Preparing:
                return new NoneAction(wrapper, changeToState);
            case Prepared:
                return new NoneAction(wrapper, changeToState);
            case Released:
                return new CommonReleaseAction(wrapper, changeToState);
            case Error:    //seek发生错误了
                return new SeekingErrorAction(wrapper, changeToState);
            case Complete: //seek到末尾发生了这个
                return new SeekingCompleteAction(wrapper, changeToState);
            case Buffering:
                return new NoneAction(wrapper, changeToState);
            case Seeking:
                return new SeekingSeekingAction(wrapper, changeToState);
            case SeekComplete:
                return new NoneAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
