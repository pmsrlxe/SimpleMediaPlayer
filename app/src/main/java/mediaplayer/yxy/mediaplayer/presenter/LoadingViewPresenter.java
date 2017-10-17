package mediaplayer.yxy.mediaplayer.presenter;

import android.view.View;

import mediaplayer.yxy.mediaplayer.listener.OnStateChangeListener;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.listener.OnPlayingBufferListener;
import mediaplayer.yxy.mediaplayer.model.LoadingViewModel;
import mediaplayer.yxy.mediaplayer.view.LoadingView;

/**
 * 用来处理loadingView
 */
public class LoadingViewPresenter {
    private final LoadingView view;
    private LoadingViewModel model;

    //preparing
    private OnStateChangeListener onStateChangeListener = new OnStateChangeListener() {
        @Override
        public void onStateChange(MediaPlayerState from, MediaPlayerState now) {
            if (now == MediaPlayerState.Preparing) {
                view.getPreparingLoadingView().setVisibility(View.VISIBLE);
            } else {
                view.getPreparingLoadingView().setVisibility(View.GONE);
            }
        }
    };

    //play buffing
    private OnPlayingBufferListener onPlayingBufferListener = new OnPlayingBufferListener() {
        @Override
        public void onPauseForBuffer() {
            view.getPlayingBufferLoadingView().setVisibility(View.VISIBLE);
        }

        @Override
        public void onPlayingFromPause() {
            view.getPlayingBufferLoadingView().setVisibility(View.GONE);
        }
    };

    public LoadingViewPresenter(LoadingView view) {
        this.view = view;
    }

    public void bind(LoadingViewModel model) {
        this.model = model;
        model.getSimpleMediaPlayer().addOnStateChangeListener(onStateChangeListener);
        model.getSimpleMediaPlayer().addOnPlayingBufferListener(onPlayingBufferListener);

    }

    public void unbind() {
        model.getSimpleMediaPlayer().removeOnStateChangeListener(onStateChangeListener);
        model.getSimpleMediaPlayer().removeOnPlayingBufferListener(onPlayingBufferListener);
    }

}
