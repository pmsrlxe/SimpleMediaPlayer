package mediaplayer.yxy.mediaplayer.action.preparing;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.action.reset.ResetStartedAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class PreparingFactory {

    private PreparingFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(wrapper, changeToState);
            case Reset:    //正在准备，突然要你reset
                return new PreparingResetAction(wrapper, changeToState);
            case Paused:   //意思是，prepared后，要pause
                return new NoneAction(wrapper, changeToState);
            case Started:  //prepared后要start
                return new ResetStartedAction(wrapper, changeToState);
            case Stopped:  //好不容易prepared了，又要stop
                return new PreparingStopAction(wrapper, changeToState);
            case Preparing:
                return new NoneAction(wrapper, changeToState);
            case Prepared: //理应发生
                return new NoneAction(wrapper, changeToState);
            case Released:
                return new PreparingReleaseAction(wrapper, changeToState);
            case Error:    //准备但是出错了
                return new PreparingErrorAction(wrapper, changeToState);
            case Complete: //准备怎么可能导致播放完毕
                return new NoneAction(wrapper, changeToState);
            case Buffering://准备不会buffer
                return new NoneAction(wrapper, changeToState);
            case Seeking:  //准备完毕，需要seek到某个地方
                return new PreparingSeekingAction(wrapper, changeToState);
            case SeekComplete: //临时状态没有啥要干的
                return new NoneAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
