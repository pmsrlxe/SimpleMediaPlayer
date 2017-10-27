package simple.media.player.listener;

/**
 * 对MediaPlayer状态
 * 了如指掌的就可以实现
 * Created by rty on 27/10/2017.
 */

public interface MediaPlayerAllAware {

    void addOnPlayingBufferListener(OnPlayingBufferListener l);

    void removeOnPlayingBufferListener(OnPlayingBufferListener l);

    void addOnStateChangeListener(OnStateChangeListener listener);

    void removeOnStateChangeListener(OnStateChangeListener listener);

    void addOnBufferChangeListener(OnBufferChangeListener l);

    void removeOnBufferChangeListener(OnBufferChangeListener l);

    void release();
}
