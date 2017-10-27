package simple.media.player.player;


import simple.media.player.data.MediaParams;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.MediaPlayerAllAware;

public interface SimpleMediaPlayer extends MediaPlayerAllAware {
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


    /**
     * 代表真正干活的的那个player
     * real的player只能给action调用！
     */
    RealMediaPlayer getRealMediaPlayer();

    /**
     * 由action调用，设置state
     *
     * @param reset
     */
    void setMediaPlayerStateFromAction(MediaPlayerState reset);
}
