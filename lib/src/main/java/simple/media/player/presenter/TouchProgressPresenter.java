package simple.media.player.presenter;

import android.content.Context;

import simple.media.player.data.MediaPlayerState;
import simple.media.player.helper.ViewTouchProgressHelper;
import simple.media.player.listener.OnStateChangeListener;
import simple.media.player.listener.OnTouchProgressChange;
import simple.media.player.model.TouchProgressModel;
import simple.media.player.utils.Utils;
import simple.media.player.view.TouchProgressView;

/**
 * 触摸的时候，展示的进度条presenter
 * Created by rty on 30/10/2017.
 */

public class TouchProgressPresenter {
    private final TouchProgressView view;
    private final ViewTouchProgressHelper viewTouchProgressHelper;
    private long downPositionMs;
    private long resultCurrent;
    private TouchProgressModel model;
    private OnStateChangeListener onStateChangeListener = new OnStateChangeListener() {
        @Override
        public void onStateChange(MediaPlayerState from, MediaPlayerState now) {
            viewTouchProgressHelper.setEnable(now.isHasDataState());
        }
    };

    public TouchProgressPresenter(Context context, TouchProgressView view) {
        this.view = view;
        viewTouchProgressHelper = new ViewTouchProgressHelper(true, view);
    }

    public void bind(final TouchProgressModel model) {
        this.model = model;
        viewTouchProgressHelper.setOnTouchProgressChange(new OnTouchProgressChange() {
            @Override
            public void onProgressChange(float percent, boolean increase) {
                long total = model.getSimpleMediaPlayer().getRuntimeInfo().getDurationInMs();
                long gap = (long) (total * percent);
                resultCurrent = downPositionMs + gap;

                view.show(resultCurrent * 1.0f / total, increase ? 1 : -1,
                        Utils.stringForTime(resultCurrent), Utils.stringForTime(total));
            }

            @Override
            public void onTouchDown() {
                downPositionMs = model.getSimpleMediaPlayer().getRuntimeInfo()
                        .getCurrentPositionInMs();
                //true就block后续touch了，因为没有数据，没有必要快进，快退
                //没有数据就返回true
            }

            @Override
            public void onTouchUp() {
                view.dismiss();
                long total = model.getSimpleMediaPlayer().getRuntimeInfo().getDurationInMs();
                //seekTo
                if (resultCurrent != downPositionMs) {
                    model.getSimpleMediaPlayer().seekToPercent((int) (resultCurrent * 1.0f / total * 100));
                }
            }
        });
        model.getSimpleMediaPlayer().addOnStateChangeListener(onStateChangeListener);
    }

    public void unbind() {
        if (model != null) {
            model.getSimpleMediaPlayer().removeOnStateChangeListener(onStateChangeListener);
        }
    }

}
