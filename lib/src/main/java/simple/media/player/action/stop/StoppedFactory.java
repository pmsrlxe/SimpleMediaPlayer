package simple.media.player.action.stop;


import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.common.CommonReleaseAction;
import simple.media.player.action.common.NoneAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class StoppedFactory {

    private StoppedFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Reset:
                return new StoppedResetAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Paused:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Playing:  //需要重新 preparing
                return new StoppedStartAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Stopped:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Preparing://重新preparing，然后pause
                return new StoppedPreparedAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Prepared:
                return new StoppedPreparedAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Error:    //stop会出错
                return new StoppedErrorAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Complete: //stop了怎么可能播放完成
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Seeking:  //stop不可能seek
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
