package mediaplayer.yxy.mediaplayer.action.seekcomplete;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class SeekCompleteFactory {

    private SeekCompleteFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState wantState) {
        switch (wantState) {
            case Reset:
                return new ScResetAction(wrapper, wantState);
            case Paused:
                return new ScPauseAction(wrapper, wantState);
            case Started:
                return new ScStartAction(wrapper, wantState);
            case Stopped:
                return new ScStopAction(wrapper, wantState);
            case Preparing:
                return new NoneAction(wrapper, wantState);
            case Prepared:
                return new NoneAction(wrapper, wantState);
            case Released:
                return new ScReleaseAction(wrapper, wantState);
            case Error:
                return new NoneAction(wrapper, wantState);
            case Complete:
                return new NoneAction(wrapper, wantState);
            case Buffering:
                return new NoneAction(wrapper, wantState);
            case Seeking:
                return new ScSeekingAction(wrapper, wantState);
            case SeekComplete:
                return new NoneAction(wrapper, wantState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
