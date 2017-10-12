package mediaplayer.yxy.mediaplayer.action.init;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none._NoneAction;
import mediaplayer.yxy.mediaplayer.action.pause.PausedReleasedAction;
import mediaplayer.yxy.mediaplayer.action.pause.PausedResetAction;
import mediaplayer.yxy.mediaplayer.action.pause.PausedStartedAction;
import mediaplayer.yxy.mediaplayer.action.pause.PausedStopedAction;
import mediaplayer.yxy.mediaplayer.action.reset.ResetPreparedAction;
import mediaplayer.yxy.mediaplayer.action.reset.ResetReleasedAction;
import mediaplayer.yxy.mediaplayer.action.reset.ResetStartedAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class InitFactory {

    private InitFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState wantState) {
        switch (wantState) {
            case Init:
                return
            case Reset:
                return getResetAction(wrapper, wantState);
            case Paused:
                return getPausedAction(wrapper, wantState);
            case Started:
                return getStartedAction(wrapper, wantState);
            case Stopped:
                return getStoppedAction(wrapper, wantState);
            case Preparing:
                return getPreparingAction(wrapper, wantState);
            case Prepared:
                return getPreparedAction(wrapper, wantState);
            case Released:
                return getReleasedAction(wrapper, wantState);
            case Error:
                return getErrorAction(wrapper, wantState);
            case Complete:
                return getCompleteAction(wrapper, wantState);
            case Buffering:
                return getBufferingAction(wrapper, wantState);
            case Seeking:
            case SeekComplete:

            default:
                throw new RuntimeException("unknown state " + wrapper.getMediaPlayerState());
        }
    }

}
