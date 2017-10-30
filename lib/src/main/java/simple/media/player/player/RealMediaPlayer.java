package simple.media.player.player;

import simple.media.player.data.MediaParams;

/**
 * 代表真正干活的的那个player
 * real的player只能给action调用！
 * <p>
 * 前头+do是为了避免和实现类方法冲突
 * <p>
 * Created by rty on 27/10/2017.
 */

public interface RealMediaPlayer {

    void doSeekTo(int positionMs) throws Throwable;

    void doPrepareAsync(MediaParams params) throws Throwable;

    void doStop() throws Throwable;

    void doStart() throws Throwable;

    void doPause() throws Throwable;

    long doGetCurrentPositionMs() throws Throwable;

    long doGetDurationMs() throws Throwable;

    void doReset() throws Throwable;

    void doRelease();
}
