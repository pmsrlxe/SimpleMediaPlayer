package simple.media.player.action.playing;


import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.common.CommonReleaseAction;
import simple.media.player.action.common.NoneAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.media.SimpleMediaPlayerImpl;

public class PlayingFactory {

    private PlayingFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayerImpl simpleMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Reset:
                return new PlayingResetAction(simpleMediaPlayer, changeToState);
            case Paused:
                return new PlayingPauseAction(simpleMediaPlayer, changeToState);
            case Playing:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Stopped:
                return new PlayingStopAction(simpleMediaPlayer, changeToState);
            case Preparing:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Prepared:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, changeToState);
            case Error:    //start还是有可能发生异常的
                return new PlayingErrorAction(simpleMediaPlayer, changeToState);
            case Complete: //播放中，有可能完成的
                return new PlayingCompleteAction(simpleMediaPlayer, changeToState);
            case PlayBuffering:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Seeking:
                return new PlayingScAction(simpleMediaPlayer, changeToState);
            case SeekComplete: //因为播放中，seek完成自动会播放
                return new PlayingScAction(simpleMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
