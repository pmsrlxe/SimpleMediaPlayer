package mediaplayer.yxy.mediaplayer.action.reset;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class ResetFactory {

    private ResetFactory() {

    }

    /**
     * reset状态下，根据不同的目标状态得到不同的action
     */
    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(wrapper, changeToState);
            case Reset:    //重复reset是可以的，reset完毕之后再次reset
                return new NoneAction(wrapper, changeToState);
            case Paused:   //reset然后preparing，然后pause
                return new ResetPauseAction(wrapper, changeToState);
            case Started:  //reset之后播放
                return new ResetStartedAction(wrapper, changeToState);
            case Stopped:  //因为stop就需要重新preparing，所以没有意义。
                return new NoneAction(wrapper, changeToState);
            case Preparing: //可以准备，但是不播放
                return new ResetPreparedAction(wrapper, changeToState);
            case Prepared: //可以准备，但是不播放
                return new ResetPreparedAction(wrapper, changeToState);
            case Released:
                return new ResetReleasedAction(wrapper, changeToState);
            case Error:    //reset 因为设置了数据源，会出现error，需要处理
                return new ResetErrorAction(wrapper, changeToState);
            case Complete:
                return new NoneAction(wrapper, changeToState);
            case Buffering:
                return new NoneAction(wrapper, changeToState);
            case Seeking:  //reset->preparing->seek
                return new ResetSeekingAction(wrapper, changeToState);
            case SeekComplete:
                return new NoneAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
