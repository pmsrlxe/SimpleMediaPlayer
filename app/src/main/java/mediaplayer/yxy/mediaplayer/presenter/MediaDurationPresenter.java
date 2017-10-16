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
    private Timer timer;
    private MediaDurationListener mediaDurationListener = null;
    private Handler handler = new Handler(Looper.getMainLooper());

    private OnMediaPlayerStateChangeListener listener = new OnMediaPlayerStateChangeListener() {
        @Override
        public void onStateChange(MediaPlayerState from, MediaPlayerState now) {
            if (!now.hasDataState()) {
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
    }


    public void setMediaDurationListener(MediaDurationListener mediaDurationListener) {
        this.mediaDurationListener = mediaDurationListener;
    }

    public void unbind() {
        stopDuration();
        model.getSimpleMediaPlayer().removeOnMediaPlayerStateChangeListener(listener);
    }

    private void stopDuration() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        Log.i(SimpleMediaPlayer.TAG, "stopDuration");
    }


    private void startDuration() {
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(createTask(), 0, this.model.getPeriod());
        Log.i(SimpleMediaPlayer.TAG, "startDuration");
    }

    private TimerTask createTask() {
        return new TimerTask() {
            @Override
            public void run() {
                if (model != null && mediaDurationListener != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            int duration = model.getSimpleMediaPlayer().getDuration();
                            int current = model.getSimpleMediaPlayer().getCurrentPosition();

                            int pc = duration == 0 ? 0 : (int) (current * 1.0f / duration * 100);
                            Log.i(SimpleMediaPlayer.TAG, "cur:" + current + ",dur:" + duration + "," + pc + "%");
                            mediaDurationListener.onUpdate(current, duration, pc);
                        }
                    });
                }
            }
        };
    }
}
