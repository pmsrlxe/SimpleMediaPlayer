package simple.media.player.presenter;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.OnBufferChangeListener;
import simple.media.player.listener.OnStateChangeListener;
import simple.media.player.media.SimpleMediaPlayer;
import simple.media.player.model.ControllerDurationModel;
import simple.media.player.utils.Utils;
import simple.media.player.view.ControllerView;

/**
 * 只管进度、时间、seekBar相关逻辑。
 */
public class ControllerDurationPresenter {
    private final ControllerView view;
    private ControllerDurationModel model;
    private Timer timer;
    private Handler handler = new Handler(Looper.getMainLooper());
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            model.getSimpleMediaPlayer().seekToPercent(seekBar.getProgress());
        }
    };

    private OnStateChangeListener listener = new OnStateChangeListener() {
        @Override
        public void onStateChange(MediaPlayerState from, MediaPlayerState now) {
            if (!now.hasDataState()) {
                stopDuration();
            } else {
                startDuration();
            }
        }
    };

    private OnBufferChangeListener bufferChangeListener = new OnBufferChangeListener() {
        @Override
        public void onBufferUpdate(int percent) {
            view.getSeekBar().setSecondaryProgress(percent);
        }
    };

    public ControllerDurationPresenter(ControllerView view) {
        this.view = view;
    }

    public void bind(ControllerDurationModel model) {
        this.model = model;
        view.getSeekBar().addOnSeekBarChangeListener(seekBarChangeListener);

        model.getSimpleMediaPlayer().addOnStateChangeListener(listener);

        //缓存 buffer
        model.getSimpleMediaPlayer().addOnBufferChangeListener(bufferChangeListener);
    }

    public void unbind() {
        stopDuration();
        view.getSeekBar().removeOnSeekBarChangeListener(seekBarChangeListener);
        model.getSimpleMediaPlayer().removeOnStateChangeListener(listener);
        model.getSimpleMediaPlayer().removeOnBufferChangeListener(bufferChangeListener);
    }

    private void stopDuration() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        Log.i(SimpleMediaPlayer.TAG, "stopDuration");
    }


    private void startDuration() {
        //停止上次的
        stopDuration();
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(createTask(), 0, this.model.getUpdatePeriodMs());
        Log.i(SimpleMediaPlayer.TAG, "startDuration");
    }

    private TimerTask createTask() {
        return new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        handleDurationUpdate();
                    }
                });
            }
        };
    }

    private void handleDurationUpdate() {
        if (model == null) {
            return;
        }

        int duration = model.getSimpleMediaPlayer().getDurationInMs();
        int current = model.getSimpleMediaPlayer().getCurrentPositionInMs();

        int pc = duration == 0 ? 0 : (int) (current * 1.0f / duration * 100);
        //Log.i(SimpleMediaPlayer.TAG, "cur:" + current + ",dur:" + duration + "," + pc + "%");

        //更新ui
        String currentFormatted = Utils.stringForTime(current);
        String durationFormatted = Utils.stringForTime(duration);
        view.getCurrentTimeTextView().setText(currentFormatted);
        view.getDurationTimeTextView().setText(durationFormatted);
        view.getSeekBar().setProgress(pc);
    }
}
