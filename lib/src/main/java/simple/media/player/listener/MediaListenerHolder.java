package simple.media.player.listener;

import java.util.ArrayList;
import java.util.List;

import simple.media.player.data.MediaPlayerState;

/**
 * 管理MediaPlayer监听器
 * Created by rty on 27/10/2017.
 */

public class MediaListenerHolder implements MediaPlayerAllAware {
    private List<OnStateChangeListener> stateChangeListeners = new ArrayList<>();
    private List<OnBufferChangeListener> onBufferChangeListeners = new ArrayList<>();
    private List<OnPlayingBufferListener> onPlayingBufferListeners = new ArrayList<>();


    //缓冲更新了
    public void notifyBufferingUpdate(int percent) {
        //通知回调
        for (OnBufferChangeListener l : onBufferChangeListeners) {
            l.onBufferUpdate(percent);
        }
    }

    //状态改变了
    public void notifyStateChangeListener(MediaPlayerState from, MediaPlayerState toState) {
        if (stateChangeListeners != null) {
            for (OnStateChangeListener l : stateChangeListeners) {
                l.onStateChange(from, toState);
            }
        }
    }

    //播放中,开始缓冲了
    public void notifyPauseForBuffer() {

    }

    //播放中缓冲结束了，开始播放
    public void notifyPlayingFromPause() {

    }

    @Override
    public void addOnPlayingBufferListener(OnPlayingBufferListener l) {
        onPlayingBufferListeners.add(l);
    }

    @Override
    public void removeOnPlayingBufferListener(OnPlayingBufferListener l) {
        onPlayingBufferListeners.remove(l);
    }

    @Override
    public void addOnStateChangeListener(OnStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        stateChangeListeners.add(listener);
    }

    @Override
    public void removeOnStateChangeListener(OnStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        stateChangeListeners.remove(listener);
    }

    @Override
    public void addOnBufferChangeListener(OnBufferChangeListener l) {
        onBufferChangeListeners.add(l);
    }

    @Override
    public void removeOnBufferChangeListener(OnBufferChangeListener l) {
        onBufferChangeListeners.remove(l);
    }

    @Override
    public void release() {
        stateChangeListeners.clear();
        onBufferChangeListeners.clear();
        onPlayingBufferListeners.clear();
    }

}
