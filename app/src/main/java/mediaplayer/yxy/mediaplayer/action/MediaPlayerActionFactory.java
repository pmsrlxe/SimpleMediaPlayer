package mediaplayer.yxy.mediaplayer.action;

import android.util.Log;

import mediaplayer.yxy.mediaplayer.action.common.NoneAction;
import mediaplayer.yxy.mediaplayer.action.complete.CompleteFactory;
import mediaplayer.yxy.mediaplayer.action.error.ErrorFactory;
import mediaplayer.yxy.mediaplayer.action.init.InitFactory;
import mediaplayer.yxy.mediaplayer.action.pause.PauseFactory;
import mediaplayer.yxy.mediaplayer.action.playing.PlayingFactory;
import mediaplayer.yxy.mediaplayer.action.prepared.PreparedFactory;
import mediaplayer.yxy.mediaplayer.action.preparing.PreparingFactory;
import mediaplayer.yxy.mediaplayer.action.release.ReleaseFactory;
import mediaplayer.yxy.mediaplayer.action.reset.ResetFactory;
import mediaplayer.yxy.mediaplayer.action.seekcomplete.SeekCompleteFactory;
import mediaplayer.yxy.mediaplayer.action.seeking.SeekingFactory;
import mediaplayer.yxy.mediaplayer.action.stop.StoppedFactory;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;

public class MediaPlayerActionFactory {

    private MediaPlayerActionFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayerImpl simpleMediaPlayer, MediaPlayerState changeToState) {
        MediaPlayerState currentState = simpleMediaPlayer.getMediaPlayerState();
        MediaPlayerAction retAction;
        switch (currentState) {
            case Init:
                retAction = InitFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            case Reset:
                retAction = ResetFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            case Paused:
                retAction = PauseFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            case Playing:
                retAction = PlayingFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            case Stopped:
                retAction = StoppedFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            case Preparing:
                retAction = PreparingFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            case Prepared:
                retAction = PreparedFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            case Released:
                retAction = ReleaseFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            case Error:
                retAction = ErrorFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            case Complete:
                retAction = CompleteFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            case Seeking:
                retAction = SeekingFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            case SeekComplete:
                retAction = SeekCompleteFactory.getAction(simpleMediaPlayer, changeToState);
                break;
            default:
                throw new RuntimeException("unknown state " + simpleMediaPlayer.getMediaPlayerState());
        }
        String msg = "select action:" + currentState + "->" + retAction.getClass().getSimpleName() + ",wish:" + changeToState;
        if (retAction.getClass() == NoneAction.class) {
            Log.e(SimpleMediaPlayer.TAG, msg);
        } else {
            Log.i(SimpleMediaPlayer.TAG, msg);
        }
        return retAction;
    }
}
