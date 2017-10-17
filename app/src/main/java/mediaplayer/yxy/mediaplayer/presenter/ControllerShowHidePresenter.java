package mediaplayer.yxy.mediaplayer.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import mediaplayer.yxy.mediaplayer.listener.OnStateChangeListener;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.model.ControllerShowHideModel;
import mediaplayer.yxy.mediaplayer.view.ControllerView;

/**
 * 只管control的显示隐藏相关逻辑
 */
public class ControllerShowHidePresenter {
    private static final int HIDE = 1988;
    private static final int SHOW = 1989;
    private final ControllerView view;
    private ControllerShowHideModel model;

    private OnStateChangeListener listener = new OnStateChangeListener() {
        @Override
        public void onStateChange(MediaPlayerState from, MediaPlayerState now) {
            //刚开始，不管怎么样，都应该是一直展示除非开始播放了。
            if (now == MediaPlayerState.Init
                    || now == MediaPlayerState.Reset
                    ) {
                showToolbar();
                return;
            }
            //暂停了，停止了，应该展示按钮
            if (now == MediaPlayerState.Paused
                    || now == MediaPlayerState.Stopped
                    || now == MediaPlayerState.Complete
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
                hideToolbar(model.getHideDuration());
            } else {
                showToolbar();
            }
        }
    };

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == HIDE) {
                onDismiss();
            }
            if (msg.what == SHOW) {
                onShow();
            }
        }
    };

    public ControllerShowHidePresenter(ControllerView view) {
        this.view = view;
    }

    public void bind(final ControllerShowHideModel model) {
        this.model = model;
        view.getSurfaceContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleShow(model.getSimpleMediaPlayer().getMediaPlayerState(),
                        view.getControlPanel().getVisibility() == View.VISIBLE);
            }
        });
        model.getSimpleMediaPlayer().addOnStateChangeListener(listener);
    }


    public void unbind() {
        if (model != null) {
            model.getSimpleMediaPlayer().removeOnStateChangeListener(listener);
        }
    }

    private void hideToolbar(int duration) {
        handler.removeMessages(SHOW);
        handler.removeMessages(HIDE);
        Message message = handler.obtainMessage(HIDE);
        handler.sendMessageDelayed(message, duration);
    }

    private void showToolbar() {
        handler.removeMessages(HIDE);
        handler.removeMessages(SHOW);
        Message message = handler.obtainMessage(SHOW);
        handler.sendMessage(message);
    }

    private void showToolbarThenDelayHide(long duration) {
        handler.removeMessages(HIDE);
        handler.removeMessages(SHOW);
        Message message = handler.obtainMessage(SHOW);
        handler.sendMessage(message);

        message = handler.obtainMessage(HIDE);
        handler.sendMessageDelayed(message, duration);
    }

    private void toggleShow(MediaPlayerState now, boolean currentIsVisible) {
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
        //暂停中，停止，或播放完毕，点击都应该是立即隐藏，立即展示
        if (now == MediaPlayerState.Paused
                || now == MediaPlayerState.Stopped
                || now == MediaPlayerState.Complete
                ) {
            if (!currentIsVisible) {
                showToolbar();
            } else {
                hideToolbar(0);
            }
            return;
        }

        //1、播放的时候，点击展示出来，需要延迟隐藏
        if (now == MediaPlayerState.Playing) {
            if (!currentIsVisible) {
                showToolbarThenDelayHide(model.getHideDuration());
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

    private void onDismiss() {
        view.getControlPanel().setVisibility(View.GONE);
        view.getCenterPlayView().setVisibility(View.GONE);
        view.getCenterPauseView().setVisibility(View.GONE);
    }

    private void onShow() {
        view.getControlPanel().setVisibility(View.VISIBLE);
        if (model.getSimpleMediaPlayer().getMediaPlayerState()
                != MediaPlayerState.Playing) {
            view.getCenterPlayView().setVisibility(View.VISIBLE);
            view.getCenterPauseView().setVisibility(View.GONE);
        } else {
            view.getCenterPlayView().setVisibility(View.GONE);
            view.getCenterPauseView().setVisibility(View.VISIBLE);
        }
    }
}
