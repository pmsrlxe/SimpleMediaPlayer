package mediaplayer.yxy.mediaplayer.model;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;

public class ControllerViewModel {
    private final SimpleMediaPlayer simpleMediaPlayer;

    public ControllerViewModel(SimpleMediaPlayer simpleMediaPlayer) {
        this.simpleMediaPlayer = simpleMediaPlayer;
    }

    public SimpleMediaPlayer getSimpleMediaPlayer() {
        return simpleMediaPlayer;
    }
}
