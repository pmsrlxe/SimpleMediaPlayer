package simple.media.player.player.exo;

import simple.media.player.data.MediaParams;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.MediaListenerHolder;
import simple.media.player.listener.OnBufferChangeListener;
import simple.media.player.listener.OnPlayingBufferListener;
import simple.media.player.listener.OnStateChangeListener;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;

/**
 * exo版本的实现
 * <p>
 * Created by rty on 27/10/2017.
 */

public class ExoMediaPlayerImpl implements SimpleMediaPlayer {
    private MediaListenerHolder listeners = new MediaListenerHolder();

    @Override
    public void reset(MediaParams mediaParams) {

    }

    @Override
    public void prepare() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void release() {

    }

    @Override
    public void seekToPercent(int percent) {

    }

    @Override
    public MediaPlayerState getMediaPlayerState() {
        return null;
    }

    @Override
    public MediaParams getMediaParams() {
        return null;
    }

    @Override
    public int getDurationInMs() {
        return 0;
    }

    @Override
    public int getCurrentPositionInMs() {
        return 0;
    }

    @Override
    public RealMediaPlayer getRealMediaPlayer() {
        return null;
    }

    @Override
    public void setMediaPlayerStateFromAction(MediaPlayerState reset) {

    }

    @Override
    public void addOnPlayingBufferListener(OnPlayingBufferListener l) {
        listeners.addOnPlayingBufferListener(l);
    }

    @Override
    public void removeOnPlayingBufferListener(OnPlayingBufferListener l) {
        listeners.removeOnPlayingBufferListener(l);
    }

    @Override
    public void addOnStateChangeListener(OnStateChangeListener listener) {
        listeners.addOnStateChangeListener(listener);
    }

    @Override
    public void removeOnStateChangeListener(OnStateChangeListener listener) {
        listeners.removeOnStateChangeListener(listener);
    }

    @Override
    public void addOnBufferChangeListener(OnBufferChangeListener l) {
        listeners.addOnBufferChangeListener(l);
    }

    @Override
    public void removeOnBufferChangeListener(OnBufferChangeListener l) {
        listeners.removeOnBufferChangeListener(l);
    }
}
