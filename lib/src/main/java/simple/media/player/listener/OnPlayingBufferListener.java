package simple.media.player.listener;

/**
 * 播放时候，因为缓存而卡住的回调
 */
public interface OnPlayingBufferListener {
    /**
     * 因为缓冲而暂停播放
     */
    void onPauseForBuffer();

    /**
     * 缓存完毕，从暂停继续播放
     */
    void onPlayingFromPause();

}
