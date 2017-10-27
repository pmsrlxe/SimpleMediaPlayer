package simple.media.player.presenter;


import android.view.View;

import simple.media.player.data.MediaPlayerState;
import simple.media.player.listener.OnPlayingBufferListener;
import simple.media.player.listener.OnStateChangeListener;
import simple.media.player.model.LoadingViewModel;
import simple.media.player.view.LoadingView;

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
