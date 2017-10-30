package simple.media.player.model;

import simple.media.player.player.SimpleMediaPlayer;

/**
 * Created by rty on 30/10/2017.
 */

public class TouchViewModel {
    private SimpleMediaPlayer simpleMediaPlayer;

    public TouchViewModel(SimpleMediaPlayer simpleMediaPlayer) {
        this.simpleMediaPlayer = simpleMediaPlayer;
    }

    public SimpleMediaPlayer getSimpleMediaPlayer() {
        return simpleMediaPlayer;
    }
}
