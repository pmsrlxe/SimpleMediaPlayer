package simple.media.player.model;


import simple.media.player.media.SimpleMediaPlayer;

public class ControllerViewModel {
    private final SimpleMediaPlayer simpleMediaPlayer;

    public ControllerViewModel(SimpleMediaPlayer simpleMediaPlayer) {
        this.simpleMediaPlayer = simpleMediaPlayer;
    }

    public SimpleMediaPlayer getSimpleMediaPlayer() {
        return simpleMediaPlayer;
    }
}
