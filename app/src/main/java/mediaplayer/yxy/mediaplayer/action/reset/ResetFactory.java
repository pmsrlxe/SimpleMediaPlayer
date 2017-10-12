package mediaplayer.yxy.mediaplayer.action.reset;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none._NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class ResetFactory {

    private ResetFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState wantState) {
        switch (wantState) {
            case Reset:
                return new _NoneAction(wrapper, wantState);
            case Paused:
                return new _NoneAction(wrapper, wantState);
            case Started:
                return new ResetStartedAction(wrapper, wantState);
            case Stopped:
                return new _NoneAction(wrapper, wantState);
            case Preparing:
                return new ResetPreparedAction(wrapper, wantState);
            case Prepared:
                return new ResetPreparedAction(wrapper, wantState);
            case Released:
                return new ResetReleasedAction(wrapper, wantState);
            case Error:
                return new _NoneAction(wrapper, wantState);
            case Complete:
                return new _NoneAction(wrapper, wantState);
            case Buffering:
                return new _NoneAction(wrapper, wantState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
