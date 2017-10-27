package simple.media.player.listener;


import simple.media.player.data.MediaPlayerState;

/**
 * 播放状态改变回调
 */
public interface OnStateChangeListener {
    void onStateChange(MediaPlayerState from, MediaPlayerState now);
}
