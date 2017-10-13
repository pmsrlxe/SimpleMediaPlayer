package mediaplayer.yxy.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import mediaplayer.yxy.mediaplayer.action.MediaPlayerAction;
import mediaplayer.yxy.mediaplayer.action.MediaPlayerActionFactory;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.data.PrepareParams;

public class SimpleMediaPlayer {
    private Context context;
    private MediaPlayer mediaPlayer;
    private MediaPlayerState mediaPlayerState = MediaPlayerState.Init;
    private MediaPlayerAction mediaPlayerAction;
    private PrepareParams prepareParams;


    public void MediaPlayer(Context ctx) {
        context = ctx;
    }


    //初始化
    public void initIfNeed() {
        if (mediaPlayer == null) {
            mediaPlayer = new LogMediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setScreenOnWhilePlaying(true);
            mediaPlayer.setOnCompletionListener(new OnCompletionListenerWrapper());
            mediaPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListenerWrapper());
            mediaPlayer.setOnSeekCompleteListener(new OnSeekCompleteListenerWrapper());
            mediaPlayer.setOnErrorListener(new OnErrorListenerWrapper());
            mediaPlayer.setOnInfoListener(new OnInfoListenerWrapper());
        }
    }

    //重置
    public void reset(PrepareParams prepareParams) {
        this.prepareParams = prepareParams;
        perform(MediaPlayerState.Reset);
    }

    //准备
    public void prepare() {
        perform(MediaPlayerState.Prepared);
    }

    //停止
    public void stop() {
        perform(MediaPlayerState.Stopped);
    }

    //暂停
    public void pause() {
        perform(MediaPlayerState.Paused);
    }

    //释放
    public void release() {
        perform(MediaPlayerState.Released);
    }

    //跳转
    public void seek(long time) {
        perform(MediaPlayerState.SeekComplete);
    }

    public MediaPlayerState getMediaPlayerState() {
        return mediaPlayerState;
    }

    public void setMediaPlayerState(MediaPlayerState mediaPlayerState) {
        this.mediaPlayerState = mediaPlayerState;
    }

    public Context getContext() {
        return context;
    }

    public PrepareParams getPrepareParams() {
        return prepareParams;
    }

    private void perform(MediaPlayerState changeToState) {
        mediaPlayerAction = MediaPlayerActionFactory.getAction(this, changeToState);
        mediaPlayerAction.perform();
    }

   /*--------------------------------listener wrapper---------------------------------------*/

    private class OnInfoListenerWrapper implements MediaPlayer.OnInfoListener {

        @Override
        public boolean onInfo(MediaPlayer mediaPlayer, int what, int extra) {
            return mediaPlayerAction != null && mediaPlayerAction.onInfo(SimpleMediaPlayer.this,
                    new MediaPlayerInfo(what, extra));
        }
    }

    private class OnErrorListenerWrapper implements MediaPlayer.OnErrorListener {

        @Override
        public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
            mediaPlayerState = MediaPlayerState.Error;
            return mediaPlayerAction != null && mediaPlayerAction.onError(SimpleMediaPlayer.this,
                    new MediaPlayerError(what, extra));
        }
    }

    private class OnSeekCompleteListenerWrapper implements MediaPlayer.OnSeekCompleteListener {

        @Override
        public void onSeekComplete(MediaPlayer mediaPlayer) {

            if (mediaPlayerAction != null) {
                mediaPlayerAction.onSeekComplete(SimpleMediaPlayer.this);
            }
        }
    }

    private class OnBufferingUpdateListenerWrapper implements MediaPlayer.OnBufferingUpdateListener {

        @Override
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
            mediaPlayerState = MediaPlayerState.Buffering;

            if (mediaPlayerAction != null) {
                mediaPlayerAction.onBufferingUpdate(SimpleMediaPlayer.this, percent);
            }
        }
    }

    private class OnCompletionListenerWrapper implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayerState = MediaPlayerState.Complete;

            if (mediaPlayerAction != null) {
                mediaPlayerAction.onCompletion(SimpleMediaPlayer.this);
            }
        }
    }
}
