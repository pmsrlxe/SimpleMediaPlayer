package simple.media.player.model;


import simple.media.player.player.SimpleMediaPlayer;

public class LoadingViewModel {
    private final SimpleMediaPlayer simpleMediaPlayer;

    public LoadingViewModel(SimpleMediaPlayer simpleMediaPlayer) {
        this.simpleMediaPlayer = simpleMediaPlayer;
    }

    public SimpleMediaPlayer getSimpleMediaPlayer() {
        return simpleMediaPlayer;
    }
}
