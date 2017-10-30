package simple.media.player.action.prepared;


import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.common.CommonReleaseAction;
import simple.media.player.action.common.NoneAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class PreparedFactory {

    private PreparedFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Reset:    //准备完了，被reset了
                return new PreparedResetAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Paused:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Playing:
                return new PreparedStartAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Stopped:
                return new PreparedStopAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Preparing:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Prepared:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Error:   //都prepared了，为什么会出错？难道是同时的buffering吗？
                return new PreparedErrorAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Complete:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Seeking:
                return new PreparedSeekingAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case SeekComplete:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
