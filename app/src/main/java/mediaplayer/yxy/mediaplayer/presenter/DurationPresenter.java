package mediaplayer.yxy.mediaplayer.presenter;

import android.os.Handler;
import android.os.Looper;

import java.util.Timer;
import java.util.TimerTask;

import mediaplayer.yxy.mediaplayer.listener.MediaDurationListener;
import mediaplayer.yxy.mediaplayer.model.DurationModel;

public class DurationPresenter {
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

    public DurationPresenter() {

    }

    public void bind(DurationModel model) {
        this.model = model;
        timer.schedule(timerTask, 0, model.getPeriod());
    }

    public void setMediaDurationListener(MediaDurationListener mediaDurationListener) {
        this.mediaDurationListener = mediaDurationListener;
    }

    public void unbind() {
        timer.cancel();
    }

}
