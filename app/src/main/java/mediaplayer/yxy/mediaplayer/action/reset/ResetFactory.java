package mediaplayer.yxy.mediaplayer.action.reset;

import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.common.CommonReleaseAction;
import mediaplayer.yxy.mediaplayer.action.common.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class ResetFactory {

    private ResetFactory() {

    }

    /**
     * reset状态下，根据不同的目标状态得到不同的action
     */
    public static MediaPlayerAction getAction(SimpleMediaPlayerImpl simpleMediaPlayer, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Reset:    //重复reset是可以的，reset完毕之后再次reset
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Paused:   //reset然后preparing，然后pause
                return new ResetPauseAction(simpleMediaPlayer, changeToState);
            case Playing:  //reset之后播放
                return new ResetPlayingAction(simpleMediaPlayer, changeToState);
            case Stopped:  //因为stop就需要重新preparing，所以没有意义。
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Preparing: //可以准备，但是不播放
                return new ResetPreparedAction(simpleMediaPlayer, changeToState);
            case Prepared: //可以准备，但是不播放
                return new ResetPreparedAction(simpleMediaPlayer, changeToState);
            case Released:
                return new CommonReleaseAction(simpleMediaPlayer, changeToState);
            case Error:    //reset 因为设置了数据源，会出现error，需要处理
                return new ResetErrorAction(simpleMediaPlayer, changeToState);
            case Complete:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case PlayBuffering:
                return new NoneAction(simpleMediaPlayer, changeToState);
            case Seeking:  //reset->preparing->seek
                return new ResetSeekingAction(simpleMediaPlayer, changeToState);
            case SeekComplete:
                return new NoneAction(simpleMediaPlayer, changeToState);
            default:
                throw new RuntimeException("unknown state  " + simpleMediaPlayer.getMediaPlayerState());
        }
    }

}
