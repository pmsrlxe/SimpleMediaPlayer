package simple.media.player.model;


import simple.media.player.media.SimpleMediaPlayer;

public class ControllerDurationModel {
    private int updatePeriodMs = 1000;
    private SimpleMediaPlayer simpleMediaPlayer;

    public ControllerDurationModel(SimpleMediaPlayer simpleMediaPlayer) {
        this.simpleMediaPlayer = simpleMediaPlayer;
    }

    public SimpleMediaPlayer getSimpleMediaPlayer() {
        return simpleMediaPlayer;
    }

    public int getUpdatePeriodMs() {
        return updatePeriodMs;
    }

    public void setUpdatePeriodMs(int updatePeriodMs) {
        this.updatePeriodMs = updatePeriodMs;
    }
}
