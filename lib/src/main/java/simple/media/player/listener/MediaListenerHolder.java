package simple.media.player.listener;

import java.util.ArrayList;
import java.util.List;

import simple.media.player.data.MediaPlayerState;

/**
 * 管理MediaPlayer监听器
 * Created by rty on 27/10/2017.
 */

public class MediaListenerHolder implements MediaPlayerAllAware {
    private final List<OnStateChangeListener> stateChangeListeners = new ArrayList<>();
    private final List<OnBufferChangeListener> onBufferChangeListeners = new ArrayList<>();
    private final List<OnPlayingBufferListener> onPlayingBufferListeners = new ArrayList<>();


    //缓冲更新了
    public void notifyBufferingUpdate(int percent) {
        synchronized (onBufferChangeListeners) {
            //通知回调
            for (OnBufferChangeListener l : onBufferChangeListeners) {
                l.onBufferUpdate(percent);
            }
        }
    }

    //状态改变了
    public void notifyStateChangeListener(MediaPlayerState from, MediaPlayerState toState) {
        synchronized (stateChangeListeners) {
            for (OnStateChangeListener l : stateChangeListeners) {
                l.onStateChange(from, toState);
            }
        }
    }

    //播放中,开始缓冲了
    public void notifyPauseForBuffer() {
        synchronized (onPlayingBufferListeners) {
            for (OnPlayingBufferListener bufferListener : onPlayingBufferListeners) {
                bufferListener.onPauseForBuffer();
            }
        }
    }

    //播放中缓冲结束了，开始播放
    public void notifyPlayingFromPause() {
        synchronized (onPlayingBufferListeners) {
            for (OnPlayingBufferListener bufferListener : onPlayingBufferListeners) {
                bufferListener.onPlayingFromPause();
            }
        }
    }

    @Override
    public void addOnPlayingBufferListener(OnPlayingBufferListener l) {
        synchronized (onPlayingBufferListeners) {
            onPlayingBufferListeners.add(l);
        }
    }

    @Override
    public void removeOnPlayingBufferListener(OnPlayingBufferListener l) {
        synchronized (onPlayingBufferListeners) {
            onPlayingBufferListeners.remove(l);
        }
    }

    @Override
    public void addOnStateChangeListener(OnStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        synchronized (stateChangeListeners) {
            stateChangeListeners.add(listener);
        }
    }

    @Override
    public void removeOnStateChangeListener(OnStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        synchronized (stateChangeListeners) {
            stateChangeListeners.remove(listener);
        }
    }

    @Override
    public void addOnBufferChangeListener(OnBufferChangeListener l) {
        synchronized (onBufferChangeListeners) {
            onBufferChangeListeners.add(l);
        }
    }

    @Override
    public void removeOnBufferChangeListener(OnBufferChangeListener l) {
        synchronized (onBufferChangeListeners) {
            onBufferChangeListeners.remove(l);
        }
    }

    @Override
    public void release() {
        synchronized (stateChangeListeners) {
            stateChangeListeners.clear();
        }
        synchronized (onBufferChangeListeners) {
            onBufferChangeListeners.clear();
        }
        synchronized (onPlayingBufferListeners) {
            onPlayingBufferListeners.clear();
        }
    }

}
