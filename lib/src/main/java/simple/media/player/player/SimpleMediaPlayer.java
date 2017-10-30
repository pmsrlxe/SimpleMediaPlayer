package simple.media.player.player;


import android.content.Context;

import simple.media.player.data.MediaParams;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.MediaPlayerAware;

/**
 * 这个接口是用来暴露给用户调用的
 */
public interface SimpleMediaPlayer extends MediaPlayerAware {
    String TAG = "SimpleMediaPlayer";

    void initIfNeed();

    Context getContext();


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
    long getDurationInMs();

    /**
     * 获取当前播放的位置
     *
     * @return 0说明没有
     */
    long getCurrentPositionInMs();

    /**
     * 由action调用，设置state
     *
     * @param state
     */
    void setMediaPlayerStateFromAction(MediaPlayerState state);
}
