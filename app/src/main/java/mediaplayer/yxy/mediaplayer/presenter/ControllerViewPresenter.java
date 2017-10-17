package mediaplayer.yxy.mediaplayer.presenter;


import android.view.View;

import mediaplayer.yxy.mediaplayer.OnStateChangeListener;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.model.ControllerDurationModel;
import mediaplayer.yxy.mediaplayer.model.ControllerViewModel;
import mediaplayer.yxy.mediaplayer.model.ControllerShowHideModel;
import mediaplayer.yxy.mediaplayer.view.ControllerView;

/**
 * 管理controller等ui控制按钮相关逻辑
 */
public class ControllerViewPresenter {
    private final ControllerView view;
    private ControllerViewModel model;
    private ControllerDurationPresenter durationPresenter;
    private ControllerShowHidePresenter visiblePresenter;

    private OnStateChangeListener listener = new OnStateChangeListener() {
        @Override
        public void onStateChange(MediaPlayerState from, MediaPlayerState now) {
            //暂停，播放按钮切换
            int panelVisibility = view.getControlPanel().getVisibility();
            if (now != MediaPlayerState.Playing) {
                //中间
                view.getCenterPauseView().setVisibility(View.GONE);
                view.getCenterPlayView().setVisibility(panelVisibility);

                //控制条上
                view.getControlPanelPauseView().setVisibility(View.GONE);
                view.getControlPanelPlayView().setVisibility(View.VISIBLE);
            } else {
                //中间
                view.getCenterPauseView().setVisibility(panelVisibility);
                view.getCenterPlayView().setVisibility(View.GONE);

                //控制条上
                view.getControlPanelPauseView().setVisibility(View.VISIBLE);
                view.getControlPanelPlayView().setVisibility(View.GONE);
            }
        }
    };

    public ControllerViewPresenter(ControllerView view) {
        this.view = view;
        durationPresenter = new ControllerDurationPresenter(view);
        visiblePresenter = new ControllerShowHidePresenter(view);
    }

    public void bind(final ControllerViewModel model) {
        this.model = model;
        model.getSimpleMediaPlayer().addOnStateChangeListener(listener);
        durationPresenter.bind(new ControllerDurationModel(model.getSimpleMediaPlayer()));
        visiblePresenter.bind(new ControllerShowHideModel(model.getSimpleMediaPlayer()));


        //主页面的按钮
        view.getCenterPlayView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.getSimpleMediaPlayer().start();
            }
        });
        view.getCenterPauseView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.getSimpleMediaPlayer().pause();
            }
        });

        //控制条上的按钮
        view.getControlPanelPlayView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.getSimpleMediaPlayer().start();
            }
        });
        view.getControlPanelPauseView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.getSimpleMediaPlayer().pause();
            }
        });
    }

    public void unbind() {
        model.getSimpleMediaPlayer().removeOnStateChangeListener(listener);
        durationPresenter.unbind();
        visiblePresenter.unbind();

    }
}
