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
import mediaplayer.yxy.mediaplayer.action.stop.StopedFactory;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class MediaPlayerActionFactory {

    private MediaPlayerActionFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState wantState) {
        MediaPlayerState currentState = wrapper.getMediaPlayerState();
        switch (currentState) {
            case Init:
                return InitFactory.getAction(wrapper, wantState);
            case Reset:
                return ResetFactory.getAction(wrapper, wantState);
            case Paused:
                return PauseFactory.getAction(wrapper, wantState);
            case Started:
                return StartedFactory.getAction(wrapper, wantState);
            case Stopped:
                return StopedFactory.getAction(wrapper, wantState);
            case Preparing:
                return PreparingFactory.getAction(wrapper, wantState);
            case Prepared:
                return PreparedFactory.getAction(wrapper, wantState);
            case Released:
                return ReleaseFactory.getAction(wrapper, wantState);
            case Error:
                return ErrorFactory.getAction(wrapper, wantState);
            case Complete:
                return CompleteFactory.getAction(wrapper, wantState);
            case Buffering:
                return BufferingFactory.getAction(wrapper, wantState);
            case Seeking:
                return SeekingFactory.getAction(wrapper, wantState);
            case SeekComplete:
                return SeekCompleteFactory.getAction(wrapper, wantState);
            default:
                throw new RuntimeException("unknown state " + wrapper.getMediaPlayerState());
        }
    }
}
