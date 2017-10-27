package simple.media.player.view;


import android.view.View;

public interface LoadingView {
    /**
     * 第一次Preparing的时候的loadingView
     */
    View getPreparingLoadingView();

    /**
     * 播放时候出现的buffering的loadingView
     */
    View getPlayingBufferLoadingView();
}
