package simple.media.player.listener;

import java.util.ArrayList;
import java.util.List;

import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;

/**
 * 管理MediaPlayer监听器
 * Created by rty on 27/10/2017.
 */
public class MediaListenersHolder implements MediaPlayerAware {
    private final List<OnStateChangeListener> stateChangeListeners = new ArrayList<>();
    private final List<OnBufferChangeListener> onBufferChangeListeners = new ArrayList<>();
    private final List<OnPlayingBufferListener> onPlayingBufferListeners = new ArrayList<>();
    private final List<OnPreparedListener> onPreparedListeners = new ArrayList<>();
    private final List<OnSeekCompleteListener> onSeekCompleteListeners = new ArrayList<>();
    private final List<OnCompleteListener> onCompleteListeners = new ArrayList<>();
    private final List<OnErrorListener> onErrorListeners = new ArrayList<>();

    /**
     * 通知出现错误了
     *
     * @param error
     */
    public void notifyError(MediaPlayerError error) {
        synchronized (onErrorListeners) {
            for (OnErrorListener l : onErrorListeners) {
                l.onError(error);
            }
        }
    }

    /**
     * 通知播放结束了
     */
    public void notifyComplete() {
        synchronized (onCompleteListeners) {
            for (OnCompleteListener l : onCompleteListeners) {
                l.onComplete();
            }
        }
    }

    /**
     * 通知seek结束了
     */
    public void notifySeekComplete() {
        synchronized (onSeekCompleteListeners) {
            for (OnSeekCompleteListener l : onSeekCompleteListeners) {
                l.onSeekComplete();
            }
        }
    }

    /**
     * 缓冲更新了
     *
     * @param percent 0-100
     */
    public void notifyBufferingUpdate(int percent) {
        synchronized (onBufferChangeListeners) {
            //通知回调
            for (OnBufferChangeListener l : onBufferChangeListeners) {
                l.onBufferUpdate(percent);
            }
        }
    }

    /**
     * 状态改变了
     */
    public void notifyStateChangeListener(MediaPlayerState from, MediaPlayerState toState) {
        synchronized (stateChangeListeners) {
            for (OnStateChangeListener l : stateChangeListeners) {
                l.onStateChange(from, toState);
            }
        }
    }

    /**
     * 播放中,开始缓冲了
     */
    public void notifyPauseForBuffer() {
        synchronized (onPlayingBufferListeners) {
            for (OnPlayingBufferListener bufferListener : onPlayingBufferListeners) {
                bufferListener.onPauseForBuffer();
            }
        }
    }

    /**
     * 播放中缓冲结束了，开始播放
     */
    public void notifyPlayingFromPause() {
        synchronized (onPlayingBufferListeners) {
            for (OnPlayingBufferListener bufferListener : onPlayingBufferListeners) {
                bufferListener.onPlayingFromPause();
            }
        }
    }

    /**
     * 通知缓存结束
     */
    public void notifyPrepared() {
        synchronized (onPreparedListeners) {
            for (OnPreparedListener l : onPreparedListeners) {
                l.onPrepared();
            }
        }
    }

    @Override
    public void addOnPlayingBufferListener(OnPlayingBufferListener l) {
        if (l == null) {
            return;
        }
        synchronized (onPlayingBufferListeners) {
            onPlayingBufferListeners.add(l);
        }
    }

    @Override
    public void removeOnPlayingBufferListener(OnPlayingBufferListener l) {
        if (l == null) {
            return;
        }
        synchronized (onPlayingBufferListeners) {
            onPlayingBufferListeners.remove(l);
        }
    }

    @Override
    public void addOnStateChangeListener(OnStateChangeListener l) {
        if (l == null) {
            return;
        }
        synchronized (stateChangeListeners) {
            stateChangeListeners.add(l);
        }
    }

    @Override
    public void removeOnStateChangeListener(OnStateChangeListener l) {
        if (l == null) {
            return;
        }
        synchronized (stateChangeListeners) {
            stateChangeListeners.remove(l);
        }
    }

    @Override
    public void addOnBufferChangeListener(OnBufferChangeListener l) {
        if (l == null) {
            return;
        }
        synchronized (onBufferChangeListeners) {
            onBufferChangeListeners.add(l);
        }
    }

    @Override
    public void removeOnBufferChangeListener(OnBufferChangeListener l) {
        if (l == null) {
            return;
        }
        synchronized (onBufferChangeListeners) {
            onBufferChangeListeners.remove(l);
        }
    }

    @Override
    public void addOnPreparedListener(OnPreparedListener l) {
        if (l == null) {
            return;
        }
        synchronized (onPreparedListeners) {
            onPreparedListeners.add(l);
        }
    }

    @Override
    public void removeOnPreparedListener(OnPreparedListener l) {
        if (l == null) {
            return;
        }
        synchronized (onPreparedListeners) {
            onPreparedListeners.remove(l);
        }
    }

    @Override
    public void addOnSeekCompleteListener(OnSeekCompleteListener l) {
        if (l == null) {
            return;
        }
        synchronized (onSeekCompleteListeners) {
            onSeekCompleteListeners.add(l);
        }
    }

    @Override
    public void removeOnSeekCompleteListener(OnSeekCompleteListener l) {
        if (l == null) {
            return;
        }
        synchronized (onSeekCompleteListeners) {
            onSeekCompleteListeners.remove(l);
        }
    }

    @Override
    public void addOnCompleteListener(OnCompleteListener l) {
        if (l == null) {
            return;
        }
        synchronized (onCompleteListeners) {
            onCompleteListeners.add(l);
        }
    }

    @Override
    public void removeOnCompleteListener(OnCompleteListener l) {
        if (l == null) {
            return;
        }
        synchronized (onCompleteListeners) {
            onCompleteListeners.remove(l);
        }
    }

    @Override
    public void addOnErrorListener(OnErrorListener l) {
        if (l == null) {
            return;
        }
        synchronized (onErrorListeners) {
            onErrorListeners.add(l);
        }
    }

    @Override
    public void removeOnErrorListener(OnErrorListener l) {
        if (l == null) {
            return;
        }
        synchronized (onErrorListeners) {
            onErrorListeners.remove(l);
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
        synchronized (onPreparedListeners) {
            onPreparedListeners.clear();
        }
        synchronized (onSeekCompleteListeners) {
            onSeekCompleteListeners.clear();
        }
        synchronized (onCompleteListeners) {
            onCompleteListeners.clear();
        }
        synchronized (onErrorListeners) {
            onErrorListeners.clear();
        }
    }

}
