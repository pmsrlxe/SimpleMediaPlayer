package simple.media.player.action.buffering;


import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.common.CommonReleaseAction;
import simple.media.player.action.common.NoneAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.sys.SysMediaPlayerImpl;

public class BufferingFactory {

    private BufferingFactory() {

    }

    public static MediaPlayerAction getAction(SysMediaPlayerImpl simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Reset:
                return new BufferingResetAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Paused:
                return new BufferingPauseAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Playing:
                return new BufferingStartAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Stopped:
                return new BufferingStopAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Preparing:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Prepared: //buffering中，怎么又回调prepared了?
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Error:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Complete:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Seeking:
                return new BufferingSeekingAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case SeekComplete:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
