package simple.media.player.action;


import android.util.Log;

import simple.media.player.action.common.NoneAction;
import simple.media.player.action.complete.CompleteFactory;
import simple.media.player.action.error.ErrorFactory;
import simple.media.player.action.init.InitFactory;
import simple.media.player.action.pause.PauseFactory;
import simple.media.player.action.playing.PlayingFactory;
import simple.media.player.action.prepared.PreparedFactory;
import simple.media.player.action.preparing.PreparingFactory;
import simple.media.player.action.release.ReleaseFactory;
import simple.media.player.action.reset.ResetFactory;
import simple.media.player.action.seekcomplete.SeekCompleteFactory;
import simple.media.player.action.seeking.SeekingFactory;
import simple.media.player.action.stop.StoppedFactory;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.media.SimpleMediaPlayer;
import simple.media.player.media.SimpleMediaPlayerImpl;

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
