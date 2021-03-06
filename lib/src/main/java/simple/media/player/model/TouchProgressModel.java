package simple.media.player.model;

import simple.media.player.player.SimpleMediaPlayer;

/**
 * 触摸的时候，展示的进度条 model
 * Created by rty on 30/10/2017.
 */

public class TouchProgressModel {

    private final SimpleMediaPlayer simpleMediaPlayer;

    public TouchProgressModel(SimpleMediaPlayer simpleMediaPlayer) {
        this.simpleMediaPlayer = simpleMediaPlayer;
    }

    public SimpleMediaPlayer getSimpleMediaPlayer() {
        return simpleMediaPlayer;
    }
}
