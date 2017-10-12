package mediaplayer.yxy.mediaplayer.action.error;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class ErrorFactory {

    private ErrorFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState wantState) {
        switch (wantState) {
            case Reset:
                return new ErrorResetAction(wrapper, wantState);
            case Paused:
                return new NoneAction(wrapper, wantState);
            case Started:
                return new NoneAction(wrapper, wantState);
            case Stopped:
                return new NoneAction(wrapper, wantState);
            case Preparing:
                return new ErrorPreparingAction(wrapper, wantState);
            case Prepared:
                return new ErrorPreparedAction(wrapper, wantState);
            case Released:
                return new ErrorReleaseAction(wrapper, wantState);
            case Error:
                return new NoneAction(wrapper, wantState);
            case Complete:
                return new NoneAction(wrapper, wantState);
            case Buffering:
                return new NoneAction(wrapper, wantState);
            case Seeking:
                return new NoneAction(wrapper, wantState);
            case SeekComplete:
                return new NoneAction(wrapper, wantState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
