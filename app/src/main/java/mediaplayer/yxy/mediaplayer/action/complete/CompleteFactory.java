package mediaplayer.yxy.mediaplayer.action.complete;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.common.CommonReleaseAction;
import mediaplayer.yxy.mediaplayer.action.common.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class CompleteFactory {

    private CompleteFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(wrapper, changeToState);
            case Reset:
                return new CompleteResetAction(wrapper, changeToState);
            case Paused:
                return new NoneAction(wrapper, changeToState);
            case Started:
                return new CompleteStartAction(wrapper, changeToState);
            case Stopped:
                return new NoneAction(wrapper, changeToState);
            case Preparing:
                return new NoneAction(wrapper, changeToState);
            case Prepared:
                return new NoneAction(wrapper, changeToState);
            case Released:
                return new CommonReleaseAction(wrapper, changeToState);
            case Error:
                return new NoneAction(wrapper, changeToState);
            case Complete:
                return new NoneAction(wrapper, changeToState);
            case Buffering:
                return new NoneAction(wrapper, changeToState);
            case Seeking:
                return new CompleteSeekingAction(wrapper, changeToState);
            case SeekComplete:
                return new NoneAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
