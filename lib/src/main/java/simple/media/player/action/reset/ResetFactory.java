package simple.media.player.action.reset;


import simple.media.player.action.MediaPlayerAction;
import simple.media.player.action.common.CommonReleaseAction;
import simple.media.player.action.common.NoneAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

public class ResetFactory {

    private ResetFactory() {

    }

    /**
     * reset状态下，根据不同的目标状态得到不同的action
     */
    public static MediaPlayerAction getAction(SimpleMediaPlayer simpleMediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Reset:    //重复reset是可以的，reset完毕之后再次reset
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Paused:   //reset然后preparing，然后pause
                return new ResetPauseAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Playing:  //reset之后播放
                return new ResetPlayingAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Stopped:  //因为stop就需要重新preparing，所以没有意义。
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Preparing: //可以准备，但是不播放
                return new ResetPreparedAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Prepared: //可以准备，但是不播放
                return new ResetPreparedAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Error:    //reset 因为设置了数据源，会出现error，需要处理
                return new ResetErrorAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Complete:
                return new NoneAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            case Seeking:  //reset->preparing->seek
                return new ResetSeekingAction(simpleMediaPlayer, realMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
