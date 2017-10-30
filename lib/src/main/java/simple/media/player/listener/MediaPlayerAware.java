package simple.media.player.listener;

/**
 * 对MediaPlayer状态
 * 了如指掌的就可以实现
 * Created by rty on 27/10/2017.
 */

public interface MediaPlayerAware {

    //playing buffer
    void addOnPlayingBufferListener(OnPlayingBufferListener l);

    void removeOnPlayingBufferListener(OnPlayingBufferListener l);

    //state Change
    void addOnStateChangeListener(OnStateChangeListener l);

    void removeOnStateChangeListener(OnStateChangeListener l);

    //bufferChange
    void addOnBufferChangeListener(OnBufferChangeListener l);

    void removeOnBufferChangeListener(OnBufferChangeListener l);

    //prepared
    void addOnPreparedListener(OnPreparedListener l);

    void removeOnPreparedListener(OnPreparedListener l);

    //seekComplete
    void addOnSeekCompleteListener(OnSeekCompleteListener l);

    void removeOnSeekCompleteListener(OnSeekCompleteListener l);

    //play Complete
    void addOnCompleteListener(OnCompleteListener l);

    void removeOnCompleteListener(OnCompleteListener l);


    //error
    void addOnErrorListener(OnErrorListener l);

    void removeOnErrorListener(OnErrorListener l);

    void release();
}
