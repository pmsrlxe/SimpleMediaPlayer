package mediaplayer.yxy.mediaplayer.action.pause;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class PauseFactory {

    private PauseFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState wantState) {
        switch (wantState) {
            case Init:
                return new NoneAction(wrapper, wantState);
            case Reset:
                return new PausedResetAction(wrapper, wantState);
            case Paused:
                return new NoneAction(wrapper, wantState);
            case Started:
                return new PausedStartedAction(wrapper, wantState);
            case Stopped:
                return new PausedStopedAction(wrapper, wantState);
            case Preparing:
                return new NoneAction(wrapper, wantState);
            case Prepared:
                return new NoneAction(wrapper, wantState);
            case Released:
                return new PausedReleasedAction(wrapper, wantState);
            case Error:
                return new NoneAction(wrapper, wantState);
            case Complete:
                return new NoneAction(wrapper, wantState);
            case Buffering:
                return new NoneAction(wrapper, wantState);
            case Seeking:
                return new PausedSeekingAction(wrapper, wantState);
            case SeekComplete:
                return new PausedCompleteAction(wrapper, wantState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }

    }

}
