package mediaplayer.yxy.mediaplayer.listener;

import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public interface OnStateChangeListener {
    void onStateChange(MediaPlayerState from, MediaPlayerState now);
}
