package mediaplayer.yxy.mediaplayer.action.complete;

import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.common.CommonReleaseAction;
import mediaplayer.yxy.mediaplayer.action.common.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class CompleteFactory {

    private CompleteFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayerImpl simpleMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Reset:
                return new CompleteResetAction(simpleMediaPlayer, changeToState);
            case Paused:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Playing:
                return new CompleteStartAction(simpleMediaPlayer, changeToState);
            case Stopped:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Preparing:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Prepared:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, changeToState);
            case Error:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Complete:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case PlayBuffering:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Seeking:
                return new CompleteSeekingAction(simpleMediaPlayer, changeToState);
            case SeekComplete:
                return new NoneAction(simpleMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
