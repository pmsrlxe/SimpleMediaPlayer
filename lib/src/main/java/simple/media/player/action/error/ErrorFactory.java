package simple.media.player.action.error;


import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.common.CommonReleaseAction;
import simple.media.player.action.common.NoneAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.SimpleMediaPlayer;

public class ErrorFactory {

    private ErrorFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer simpleMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Reset:
                return new ErrorResetAction(simpleMediaPlayer, changeToState);
            case Paused:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Playing:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Stopped:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Preparing:
                return new ErrorPreparingAction(simpleMediaPlayer, changeToState);
            case Prepared:
                return new ErrorPreparedAction(simpleMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, changeToState);
            case Error:    //error了后，又tm发生error了
                return new ErrorErrorAction(simpleMediaPlayer, changeToState);
            case Complete:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case PlayBuffering: //error后，怎么可能继续buffering？
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Seeking:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case SeekComplete:
                return new NoneAction(simpleMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
