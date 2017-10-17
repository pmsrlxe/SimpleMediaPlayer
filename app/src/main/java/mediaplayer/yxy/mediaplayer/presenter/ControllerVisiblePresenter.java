package mediaplayer.yxy.mediaplayer.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import mediaplayer.yxy.mediaplayer.OnMediaPlayerStateChangeListener;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.listener.ToolBarVisibleListener;
import mediaplayer.yxy.mediaplayer.model.ToolBarVisibleModel;
import mediaplayer.yxy.mediaplayer.view.ControllerView;

/**
 * 只管control的显示隐藏相关逻辑
 */
public class ControllerVisiblePresenter {
    private static final int HIDE = 1988;
    private static final int SHOW = 1989;
    private final ControllerView view;
    private ToolBarVisibleListener toolBarVisibleListener;
    private ToolBarVisibleModel model;
    private OnMediaPlayerStateChangeListener listener = new OnMediaPlayerStateChangeListener() {
        @Override
        public void onStateChange(MediaPlayerState from, MediaPlayerState now) {
            if (now == MediaPlayerState.Init
                    || now == MediaPlayerState.Reset
                    ) {
                showToolbar();
                return;
            }

            //立即隐藏
            if (now == MediaPlayerState.Seeking
                    || now == MediaPlayerState.Preparing
                    ) {
                hideToolbar(0);
                return;
            }

            //隐藏：
            //播放中，loading中
            if (now == MediaPlayerState.Playing) {
                hideToolbarByModel();
            } else {
                showToolbar();
            }
        }
    };

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (toolBarVisibleListener != null) {
                if (msg.what == HIDE) {
                    toolBarVisibleListener.onDismiss();
                }
                if (msg.what == SHOW) {
                    toolBarVisibleListener.onShow();
                }
            }
        }
    };

    public ControllerVisiblePresenter(ControllerView view) {
        this.view = view;
    }

    public void bind(final ToolBarVisibleModel model) {
        this.model = model;
        view.getSurfaceContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleShow(model.getSimpleMediaPlayer().getMediaPlayerState(),
                        view.getControlPanel().getVisibility() == View.VISIBLE);
            }
        });
        setToolBarVisibleListener(new ToolBarVisibleListener() {
            @Override
            public void onDismiss() {
                view.getControlPanel().setVisibility(View.GONE);
                view.getCenterStartView().setVisibility(View.GONE);
                view.getCenterPauseView().setVisibility(View.GONE);
            }

            @Override
            public void onShow() {
                view.getControlPanel().setVisibility(View.VISIBLE);
                if (model.getSimpleMediaPlayer().getMediaPlayerState()
                        != MediaPlayerState.Playing) {
                    view.getCenterStartView().setVisibility(View.VISIBLE);
                    view.getCenterPauseView().setVisibility(View.GONE);
                } else {
                    view.getCenterStartView().setVisibility(View.GONE);
                    view.getCenterPauseView().setVisibility(View.VISIBLE);
                }
            }
        });
        model.getSimpleMediaPlayer().addOnMediaPlayerStateChangeListener(listener);
    }


    public void unbind() {
        if (model != null) {
            model.getSimpleMediaPlayer().removeOnMediaPlayerStateChangeListener(listener);
        }
    }

    private void hideToolbar(int duration) {
        handler.removeMessages(HIDE);
        Message message = handler.obtainMessage(HIDE);
        handler.sendMessageDelayed(message, duration);
    }

    private void showToolbar() {
        handler.removeMessages(SHOW);
        Message message = handler.obtainMessage(SHOW);
        handler.sendMessage(message);
    }

    public void setToolBarVisibleListener(ToolBarVisibleListener toolBarVisibleListener) {
        this.toolBarVisibleListener = toolBarVisibleListener;
    }

    public void toggleShow(MediaPlayerState now, boolean currentIsVisible) {
        //刚开始，没有播放过，点击屏幕，控制条不要消失
        if (now == MediaPlayerState.Init || now == MediaPlayerState.Reset) {
            showToolbar();
            return;
        }
        //preparing过程，有loading框，不能展示
        if (now == MediaPlayerState.Preparing) {
            hideToolbar(0);
            return;
        }

        //1、播放的时候，点击展示出来，需要延迟隐藏
        if (now == MediaPlayerState.Playing) {
            if (!currentIsVisible) {
                showToolbar();
                hideToolbarByModel();
            } else {
                hideToolbar(0);
            }
        } else {
            //2、其他情况下，点击瞬间出来，瞬间消失
            if (!currentIsVisible) {
                showToolbar();
            } else {
                hideToolbar(0);
            }
        }
    }

    private void hideToolbarByModel() {
        hideToolbar(model == null ? 2500 : model.getHideDuration());
    }
}
