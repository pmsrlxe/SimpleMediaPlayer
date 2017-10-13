package mediaplayer.yxy.mediaplayer.action.error;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class ErrorFactory {

    private ErrorFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(wrapper, changeToState);
            case Reset:
                return new ErrorResetAction(wrapper, changeToState);
            case Paused:
                return new NoneAction(wrapper, changeToState);
            case Started:
                return new NoneAction(wrapper, changeToState);
            case Stopped:
                return new NoneAction(wrapper, changeToState);
            case Preparing:
                return new ErrorPreparingAction(wrapper, changeToState);
            case Prepared:
                return new ErrorPreparedAction(wrapper, changeToState);
            case Released:
                return new ErrorReleaseAction(wrapper, changeToState);
            case Error:    //error了后，又tm发生error了
                return new ErrorErrorAction(wrapper, changeToState);
            case Complete:
                return new NoneAction(wrapper, changeToState);
            case Buffering: //error后，怎么可能继续buffering？
                return new NoneAction(wrapper, changeToState);
            case Seeking:
                return new NoneAction(wrapper, changeToState);
            case SeekComplete:
                return new NoneAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
