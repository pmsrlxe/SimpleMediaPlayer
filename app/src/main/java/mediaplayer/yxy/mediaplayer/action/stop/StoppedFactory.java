package mediaplayer.yxy.mediaplayer.action.stop;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.none.NoneAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class StoppedFactory {

    private StoppedFactory() {

    }

    public static MediaPlayerAction getAction(SimpleMediaPlayer wrapper, MediaPlayerState changeToState) {
        switch (changeToState) {
            case Init:
                return new NoneAction(wrapper, changeToState);
            case Reset:
                return new StoppedResetAction(wrapper, changeToState);
            case Paused:
                return new NoneAction(wrapper, changeToState);
            case Started:  //需要重新 preparing
                return new StoppedStartAction(wrapper, changeToState);
            case Stopped:
                return new NoneAction(wrapper, changeToState);
            case Preparing://重新preparing，然后pause
                return new StoppedPreparedAction(wrapper, changeToState);
            case Prepared:
                return new StoppedPreparedAction(wrapper, changeToState);
            case Released:
                return new StoppedReleaseAction(wrapper, changeToState);
            case Error:    //stop会出错
                return new StoppedErrorAction(wrapper, changeToState);
            case Complete: //stop了怎么可能播放完成
                return new NoneAction(wrapper, changeToState);
            case Buffering:
                return new NoneAction(wrapper, changeToState);
            case Seeking:  //stop不可能seek
                return new NoneAction(wrapper, changeToState);
            case SeekComplete:
                return new NoneAction(wrapper, changeToState);
            default:
                throw new RuntimeException("unknown state  " + wrapper.getMediaPlayerState());
        }
    }

}
