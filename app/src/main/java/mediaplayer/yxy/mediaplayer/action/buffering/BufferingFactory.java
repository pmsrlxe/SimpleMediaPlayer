package mediaplayer.yxy.mediaplayer.action.buffering;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class BufferingFactory {

    private BufferingFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(wrapper, changeToState);
            case Reset:
                return new BufferingResetAction(wrapper, changeToState);
            case Paused:
                return new BufferingPauseAction(wrapper, changeToState);
            case Started:
                return new BufferingStartAction(wrapper, changeToState);
            case Stopped:
                return new BufferingStopAction(wrapper, changeToState);
            case Preparing:
                return new NoneAction(wrapper, changeToState);
            case Prepared: //buffering中，怎么又回调prepared了?
                return new NoneAction(wrapper, changeToState);
            case Released:
                return new BufferingReleaseAction(wrapper, changeToState);
            case Error:
                return new NoneAction(wrapper, changeToState);
            case Complete:
                return new NoneAction(wrapper, changeToState);
            case Buffering:
                return new BufferingErrorAction(wrapper, changeToState);
            case Seeking:
                return new BufferingSeekingAction(wrapper, changeToState);
            case SeekComplete:
                return new NoneAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
