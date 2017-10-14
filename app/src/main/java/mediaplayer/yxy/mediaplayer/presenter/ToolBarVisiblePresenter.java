package mediaplayer.yxy.mediaplayer.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.listener.ToolBarVisibleListener;
import mediaplayer.yxy.mediaplayer.model.ToolBarVisibleModel;

public class ToolBarVisiblePresenter {
    private static final int HIDE = 1988;
    private static final int SHOW = 1989;
    private ToolBarVisibleListener toolBarVisibleListener;
    private ToolBarVisibleModel model;

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

    public ToolBarVisiblePresenter() {
    }

    public void bind(ToolBarVisibleModel model) {
        this.model = model;
    }

    private void postHide(int duration) {
        handler.removeMessages(HIDE);
        Message message = handler.obtainMessage(HIDE);
        handler.sendMessageDelayed(message, duration);
    }

    private void postShow() {
        handler.removeMessages(SHOW);
        Message message = handler.obtainMessage(SHOW);
        handler.sendMessage(message);
    }

    public void setToolBarVisibleListener(ToolBarVisibleListener toolBarVisibleListener) {
        this.toolBarVisibleListener = toolBarVisibleListener;
    }

    public void notifyStateChange(MediaPlayerState now) {
        if (now == MediaPlayerState.Playing) {
            postHideByModel();
        } else if (now == MediaPlayerState.Paused) {
            postShow();
        }
    }

    public void toggleShow(MediaPlayerState now, boolean currentIsVisible) {
        //1、播放的时候，点击展示出来，需要延迟隐藏
        if (now == MediaPlayerState.Playing) {
            if (!currentIsVisible) {
                postShow();
                postHideByModel();
            } else {
                postHide(0);
            }
        } else {
            //2、其他情况下，点击瞬间出来，瞬间消失
            if (!currentIsVisible) {
                postShow();
            } else {
                postHide(0);
            }
        }
    }

    private void postHideByModel() {
        postHide(model == null ? 2500 : model.getHideDuration());
    }
}
