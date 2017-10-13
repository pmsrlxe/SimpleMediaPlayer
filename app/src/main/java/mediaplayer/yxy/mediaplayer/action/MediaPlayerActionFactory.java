package mediaplayer.yxy.mediaplayer.action;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.buffering.BufferingFactory;
import mediaplayer.yxy.mediaplayer.action.complete.CompleteFactory;
import mediaplayer.yxy.mediaplayer.action.error.ErrorFactory;
import mediaplayer.yxy.mediaplayer.action.init.InitFactory;
import mediaplayer.yxy.mediaplayer.action.pause.PauseFactory;
import mediaplayer.yxy.mediaplayer.action.prepared.PreparedFactory;
import mediaplayer.yxy.mediaplayer.action.preparing.PreparingFactory;
import mediaplayer.yxy.mediaplayer.action.release.ReleaseFactory;
import mediaplayer.yxy.mediaplayer.action.reset.ResetFactory;
import mediaplayer.yxy.mediaplayer.action.seekcomplete.SeekCompleteFactory;
import mediaplayer.yxy.mediaplayer.action.seeking.SeekingFactory;
import mediaplayer.yxy.mediaplayer.action.started.StartedFactory;
import mediaplayer.yxy.mediaplayer.action.stop.StoppedFactory;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class MediaPlayerActionFactory {

    private MediaPlayerActionFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        MediaPlayerState currentState = wrapper.getMediaPlayerState();
        switch (currentState) {
            case Init:
                return InitFactory.getAction(wrapper, changeToState);
            case Reset:
                return ResetFactory.getAction(wrapper, changeToState);
            case Paused:
                return PauseFactory.getAction(wrapper, changeToState);
            case Started:
                return StartedFactory.getAction(wrapper, changeToState);
            case Stopped:
                return StoppedFactory.getAction(wrapper, changeToState);
            case Preparing:
                return PreparingFactory.getAction(wrapper, changeToState);
            case Prepared:
                return PreparedFactory.getAction(wrapper, changeToState);
            case Released:
                return ReleaseFactory.getAction(wrapper, changeToState);
            case Error:
                return ErrorFactory.getAction(wrapper, changeToState);
            case Complete:
                return CompleteFactory.getAction(wrapper, changeToState);
            case Buffering:
                return BufferingFactory.getAction(wrapper, changeToState);
            case Seeking:
                return SeekingFactory.getAction(wrapper, changeToState);
            case SeekComplete:
                return SeekCompleteFactory.getAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state " + wrapper.getMediaPlayerState());
        }
    }
}
