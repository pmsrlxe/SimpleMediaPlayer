package mediaplayer.yxy.mediaplayer.media;

import mediaplayer.yxy.mediaplayer.data.MediaParams;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.listener.OnBufferChangeListener;
import mediaplayer.yxy.mediaplayer.listener.OnPlayingBufferListener;
import mediaplayer.yxy.mediaplayer.listener.OnStateChangeListener;

public interface SimpleMediaPlayer {
    String TAG = "SimpleMediaPlayer";

    /**
     * 重置player
     */
    void reset(MediaParams mediaParams);

    /**
     * 准备播放
     */
    void prepare();

    /**
     * 播放
     */
    void start();

    /**
     * 停止
     */
    void stop();

    /**
     * 暂停
     */
    void pause();

    /**
     * 释放
     */
    void release();

    /**
     * 跳转 0-100
     */
    void seekToPercent(int percent);

    /**
     * 获取当前player的状态
     */
    MediaPlayerState getMediaPlayerState();

    /**
     * 获取当前player的参数
     */
    MediaParams getMediaParams();

    /**
     * 获取当前的player长度
     *
     * @return 0说明没有
     */
    int getDurationInMs();

    /**
     * 获取当前播放的位置
     *
     * @return 0说明没有
     */
    int getCurrentPositionInMs();


    /*--------------------------------listeners---------------------------------------*/

    void addOnPlayingBufferListener(OnPlayingBufferListener l);

    void removeOnPlayingBufferListener(OnPlayingBufferListener l);

    void addOnStateChangeListener(OnStateChangeListener listener);

    void removeOnStateChangeListener(OnStateChangeListener listener);

    void addOnBufferChangeListener(OnBufferChangeListener l);

    void removeOnBufferChangeListener(OnBufferChangeListener l);
}
