package mediaplayer.yxy.mediaplayer;

import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public interface OnStateChangeListener {
    void onStateChange(MediaPlayerState from, MediaPlayerState now);
}
