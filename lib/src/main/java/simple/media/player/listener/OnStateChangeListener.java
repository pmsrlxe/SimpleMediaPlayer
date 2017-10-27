package simple.media.player.listener;


import simple.media.player.data.MediaPlayerState;

public interface OnStateChangeListener {
    void onStateChange(MediaPlayerState from, MediaPlayerState now);
}
