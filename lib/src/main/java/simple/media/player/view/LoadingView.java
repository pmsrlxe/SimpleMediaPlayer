package simple.media.player.view;


import android.view.View;

public interface LoadingView {
    /**
     * Preparing的时候的loadingView
     */
    View getPreparingLoadingView();

    /**
     * 播放时候出现的buffering的loadingView
     */
    View getPlayingBufferLoadingView();
}
