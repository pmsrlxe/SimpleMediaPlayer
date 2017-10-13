package mediaplayer.yxy.mediaplayer;

import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public interface OnMediaPlayerStateChangeListener {
    void onStateChange(MediaPlayerState from, MediaPlayerState now);
}
