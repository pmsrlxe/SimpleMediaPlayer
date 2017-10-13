package mediaplayer.yxy.mediaplayer.action;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
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

    public static MediaPlayerAction getAction(SimpleMediaPlayer simpleMediaPlayer, MediaPlayerState changeToState) {
        MediaPlayerState currentState = simpleMediaPlayer.getMediaPlayerState();
        switch (currentState) {
            case Init:
                return InitFactory.getAction(simpleMediaPlayer, changeToState);
            case Reset:
                return ResetFactory.getAction(simpleMediaPlayer, changeToState);
            case Paused:
                return PauseFactory.getAction(simpleMediaPlayer, changeToState);
            case Started:
                return StartedFactory.getAction(simpleMediaPlayer, changeToState);
            case Stopped:
                return StoppedFactory.getAction(simpleMediaPlayer, changeToState);
            case Preparing:
                return PreparingFactory.getAction(simpleMediaPlayer, changeToState);
            case Prepared:
                return PreparedFactory.getAction(simpleMediaPlayer, changeToState);
            case Released:
                return ReleaseFactory.getAction(simpleMediaPlayer, changeToState);
            case Error:
                return ErrorFactory.getAction(simpleMediaPlayer, changeToState);
            case Complete:
                return CompleteFactory.getAction(simpleMediaPlayer, changeToState);
            case Seeking:
                return SeekingFactory.getAction(simpleMediaPlayer, changeToState);
            case SeekComplete:
                return SeekCompleteFactory.getAction(simpleMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state " + simpleMediaPlayer.getMediaPlayerState());
        }
    }
}
