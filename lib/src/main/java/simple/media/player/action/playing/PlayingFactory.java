package simple.media.player.action.playing;


import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.common.CommonReleaseAction;
import simple.media.player.action.common.NoneAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PlayingFactory {

    private PlayingFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Reset:
                return new PlayingResetAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Paused:
                return new PlayingPauseAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Playing:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Stopped:
                return new PlayingStopAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Preparing:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Prepared:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Error:    //start还是有可能发生异常的
                return new PlayingErrorAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Complete: //播放中，有可能完成的
                return new PlayingCompleteAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Seeking:
                return new PlayingScAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case SeekComplete: //因为播放中，seek完成自动会播放
                return new PlayingScAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
