package mediaplayer.yxy.mediaplayer.action.preparing;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.action.reset.ResetStartedAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class PreparingFactory {

    private PreparingFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState wantState) {
        switch (wantState) {
            case Reset:
                return new PreparingResetAction(wrapper, wantState);
            case Paused:
                return new NoneAction(wrapper, wantState);
            case Started:
                return new ResetStartedAction(wrapper, wantState);
            case Stopped:
                return new PreparingStopAction(wrapper, wantState);
            case Preparing:
                return new NoneAction(wrapper, wantState);
            case Prepared:
                return new NoneAction(wrapper, wantState);
            case Released:
                return new PreparingReleaseAction(wrapper, wantState);
            case Error:
                return new NoneAction(wrapper, wantState);
            case Complete:
                return new NoneAction(wrapper, wantState);
            case Buffering:
                return new NoneAction(wrapper, wantState);
            case Seeking:
                return new PreparingSeekingAction(wrapper, wantState);
            case SeekComplete:
                return new NoneAction(wrapper, wantState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
