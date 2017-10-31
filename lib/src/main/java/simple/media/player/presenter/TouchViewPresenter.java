package simple.media.player.presenter;

import android.content.Context;
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

        touchProgressPresenter = new TouchProgressPresenter(context, progressView);
        touchVolumePresenter = new TouchVolumePresenter(context, touchVolumeView);
    }

    public void bind(TouchViewModel model) {
        touchProgressPresenter.bind(new TouchProgressModel(model.getSimpleMediaPlayer()));
        touchVolumePresenter.bind(new TouchVolumeModel());

        TouchListenersHolder.getInstance().addTouchListener(panelToTouch, progressView.getOnTouchListener());
        TouchListenersHolder.getInstance().addTouchListener(panelToTouch, touchVolumeView.getOnTouchListener());
    }

    public void unbind() {
        TouchListenersHolder.getInstance().removeTouchListener(panelToTouch, progressView.getOnTouchListener());
        TouchListenersHolder.getInstance().removeTouchListener(panelToTouch, touchVolumeView.getOnTouchListener());
        touchVolumePresenter.unbind();
        touchProgressPresenter.unbind();
        this.panelToTouch.setOnTouchListener(null);
    }

}
