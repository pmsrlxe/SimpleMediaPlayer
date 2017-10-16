package mediaplayer.yxy.mediaplayer.model;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;

public class ControllerDurationModel {
    private int period = 1000;
    private SimpleMediaPlayer simpleMediaPlayer;

    public ControllerDurationModel(SimpleMediaPlayer simpleMediaPlayer) {
        this.simpleMediaPlayer = simpleMediaPlayer;
    }

    public SimpleMediaPlayer getSimpleMediaPlayer() {
        return simpleMediaPlayer;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
