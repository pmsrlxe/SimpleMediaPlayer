package mediaplayer.yxy.mediaplayer.action.seekcomplete;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class SeekCompleteFactory {

    private SeekCompleteFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(wrapper, changeToState);
            case Reset:
                return new ScResetAction(wrapper, changeToState);
            case Paused:
                return new ScPauseAction(wrapper, changeToState);
            case Started:
                return new ScStartAction(wrapper, changeToState);
            case Stopped:
                return new ScStopAction(wrapper, changeToState);
            case Preparing:
                return new NoneAction(wrapper, changeToState);
            case Prepared:
                return new NoneAction(wrapper, changeToState);
            case Released:
                return new ScReleaseAction(wrapper, changeToState);
            case Error:
                return new NoneAction(wrapper, changeToState);
            case Complete:
                return new NoneAction(wrapper, changeToState);
            case Buffering:
                return new NoneAction(wrapper, changeToState);
            case Seeking:
                return new ScSeekingAction(wrapper, changeToState);
            case SeekComplete:
                return new NoneAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
