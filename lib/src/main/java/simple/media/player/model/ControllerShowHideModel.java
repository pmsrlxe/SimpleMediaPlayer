package simple.media.player.model;


import simple.media.player.media.SimpleMediaPlayer;

public class ControllerShowHideModel {

    private int hideDuration = 2500;
    private SimpleMediaPlayer simpleMediaPlayer;

    public ControllerShowHideModel(SimpleMediaPlayer simpleMediaPlayer) {
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
