package simple.media.player.action.complete;


import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.common.CommonReleaseAction;
import simple.media.player.action.common.NoneAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class CompleteFactory {

    private CompleteFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Reset:
                return new CompleteResetAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Paused:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Playing:
                return new CompleteStartAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Stopped:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Preparing:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Prepared:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Error:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Complete:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case PlayBuffering:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Seeking:
                return new CompleteSeekingAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case SeekComplete:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
