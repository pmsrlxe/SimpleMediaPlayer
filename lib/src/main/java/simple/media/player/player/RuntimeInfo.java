package simple.media.player.player;

import simple.media.player.data.MediaPlayerState;

/**
 * MediaPlayer存在的时候的各种数据
 * Created by rty on 30/10/2017.
 */

public class RuntimeInfo {
    private long currentPositionInMs; //目前播放到的位置
    private long durationInMs; //总的时间长度
    private MediaPlayerState currentState; //目前的状态

    public RuntimeInfo(long currentPositionInMs, long durationInMs, MediaPlayerState currentState) {
        this.currentPositionInMs = currentPositionInMs;
        this.durationInMs = durationInMs;
        this.currentState = currentState;
    }

    public long getCurrentPositionInMs() {
        return currentPositionInMs;
    }

    public long getDurationInMs() {
        return durationInMs;
    }
}
