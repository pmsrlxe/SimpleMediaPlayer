package mediaplayer.yxy.mediaplayer.action.stop;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class StoppedFactory {

    private StoppedFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState wantState) {
        switch (wantState) {
            case Reset:
                return new StoppedResetAction(wrapper, wantState);
            case Paused:
                return new NoneAction(wrapper, wantState);
            case Started:
                return new StoppedStartAction(wrapper, wantState);
            case Stopped:
                return new NoneAction(wrapper, wantState);
            case Preparing:
                return new StoppedPreparingAction(wrapper, wantState);
            case Prepared:
                return new StoppedPreparingAction(wrapper, wantState);
            case Released:
                return new StoppedReleaseAction(wrapper, wantState);
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
