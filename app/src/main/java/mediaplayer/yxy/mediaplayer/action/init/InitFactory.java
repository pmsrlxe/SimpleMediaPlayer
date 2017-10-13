package mediaplayer.yxy.mediaplayer.action.init;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class InitFactory {

    private InitFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(wrapper, changeToState);
            case Reset:    //init之后可以reset了
                return new InitResetAction(wrapper, changeToState);
            case Paused:
                return new NoneAction(wrapper, changeToState);
            case Started:
                return new NoneAction(wrapper, changeToState);
            case Stopped:
                return new NoneAction(wrapper, changeToState);
            case Preparing:
                return new NoneAction(wrapper, changeToState);
            case Prepared:
                return new NoneAction(wrapper, changeToState);
            case Released:
                return new InitReleaseAction(wrapper, changeToState);
            case Error:    //init不可能发生error
                return new NoneAction(wrapper, changeToState);
            case Complete:
                return new NoneAction(wrapper, changeToState);
            case Buffering:
                return new NoneAction(wrapper, changeToState);
            case Seeking:
                return new NoneAction(wrapper, changeToState);
            case SeekComplete:
                return new NoneAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state " + wrapper.getMediaPlayerState());
        }
    }

}
