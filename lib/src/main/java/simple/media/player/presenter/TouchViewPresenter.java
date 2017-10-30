package simple.media.player.presenter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import simple.media.player.helper.TouchListenersHolder;
import simple.media.player.model.TouchProgressModel;
import simple.media.player.model.TouchViewModel;
import simple.media.player.model.TouchVolumeModel;
import simple.media.player.view.TouchProgressView;
import simple.media.player.view.TouchVolumeView;

/**
 * 触摸相关的
 * Created by rty on 30/10/2017.
 */

public class TouchViewPresenter {
    private final TouchProgressPresenter touchProgressPresenter;
    private final TouchVolumePresenter touchVolumePresenter;
    private final TouchListenersHolder holder;
    private final TouchProgressView progressView;
    private final TouchVolumeView touchVolumeView;
    private final View panelToTouch;

    public TouchViewPresenter(Context context,
                              View panelToTouch,
                              TouchProgressView progressView,
                              TouchVolumeView touchVolumeView) {
        this.progressView = progressView;
        this.touchVolumeView = touchVolumeView;
        this.panelToTouch = panelToTouch;

        holder = new TouchListenersHolder();

        touchProgressPresenter = new TouchProgressPresenter(context, progressView);
        touchVolumePresenter = new TouchVolumePresenter(context, touchVolumeView);
    }

    public void bind(TouchViewModel model) {
        touchProgressPresenter.bind(new TouchProgressModel(model.getSimpleMediaPlayer()));
        touchVolumePresenter.bind(new TouchVolumeModel());

        holder.addTouchListener(progressView.getOnTouchListener());
        holder.addTouchListener(touchVolumeView.getOnTouchListener());

        this.panelToTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                holder.onTouch(v, event);
                return false;
            }
        });
    }

    public void release() {
        holder.removeTouchListener(progressView.getOnTouchListener());
        holder.removeTouchListener(touchVolumeView.getOnTouchListener());
        this.panelToTouch.setOnTouchListener(null);
    }

}
