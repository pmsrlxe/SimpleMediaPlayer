package mediaplayer.yxy.mediaplayer.presenter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import mediaplayer.yxy.mediaplayer.OnMediaPlayerStateChangeListener;
import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.listener.MediaDurationListener;
import mediaplayer.yxy.mediaplayer.model.DurationModel;

public class MediaDurationPresenter {
    private DurationModel model;
    private Timer timer = new Timer();
    private MediaDurationListener mediaDurationListener = null;
    private Handler handler = new Handler(Looper.getMainLooper());
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (model != null && mediaDurationListener != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int duration = model.getSimpleMediaPlayer().getDuration();
                        int current = model.getSimpleMediaPlayer().getCurrentPosition();

                        int pc = duration == 0 ? 0 : (int) (current * 1.0f / duration * 100);
                        mediaDurationListener.onUpdate(current, duration, pc);
                    }
                });
            }
        }
    };

    OnMediaPlayerStateChangeListener listener = new OnMediaPlayerStateChangeListener() {
        @Override
        public void onStateChange(MediaPlayerState from, MediaPlayerState now) {
            if (now != MediaPlayerState.Playing) {
                stopDuration();
            } else {
                startDuration();
            }
        }
    };

    public MediaDurationPresenter() {

    }

    public void bind(DurationModel model) {
        this.model = model;
        model.getSimpleMediaPlayer().addOnMediaPlayerStateChangeListener(listener);
        startDuration();
    }


    public void setMediaDurationListener(MediaDurationListener mediaDurationListener) {
        this.mediaDurationListener = mediaDurationListener;
    }

    public void unbind() {
        stopDuration();
        model.getSimpleMediaPlayer().removeOnMediaPlayerStateChangeListener(listener);
    }

    private void stopDuration() {
        timer.cancel();
        Log.e(SimpleMediaPlayer.TAG, "stopDuration");
    }


    private void startDuration() {
        timer.schedule(timerTask, 0, this.model.getPeriod());
        Log.e(SimpleMediaPlayer.TAG, "startDuration");
    }
}
