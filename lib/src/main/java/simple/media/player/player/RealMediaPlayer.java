package simple.media.player.player;

/**
 * 代表真正干活的的那个player
 * real的player只能给action调用！
 * Created by rty on 27/10/2017.
 */

public interface RealMediaPlayer {

    void seekTo(int msSecond) throws Throwable;

    void prepare() throws Throwable;

    void stop() throws Throwable;

    void start() throws Throwable;

    void pause() throws Throwable;

    int getCurrentPosition() throws Throwable;

    int getDuration() throws Throwable;

    /**
     * reset下，并且设置下数据源
     */
    void resetAndSetSource(String url) throws Throwable;

    void prepareAsync() throws Throwable;

    void release();
}
