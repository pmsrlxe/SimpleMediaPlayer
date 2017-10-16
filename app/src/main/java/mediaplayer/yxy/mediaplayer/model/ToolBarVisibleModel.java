package mediaplayer.yxy.mediaplayer.model;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;

public class ToolBarVisibleModel {

    private int hideDuration = 2500;
    private SimpleMediaPlayer simpleMediaPlayer;

    public ToolBarVisibleModel(SimpleMediaPlayer simpleMediaPlayer) {
        this.simpleMediaPlayer = simpleMediaPlayer;
    }

    public SimpleMediaPlayer getSimpleMediaPlayer() {
        return simpleMediaPlayer;
    }

    public void setHideDuration(int hideDuration) {
        this.hideDuration = hideDuration;
    }

    public int getHideDuration() {
        return hideDuration;
    }
}
