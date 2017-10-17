package mediaplayer.yxy.mediaplayer.presenter;

import android.view.View;
import android.widget.TextView;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.data.MediaParams;
import mediaplayer.yxy.mediaplayer.model.ControllerViewModel;
import mediaplayer.yxy.mediaplayer.model.LoadingViewModel;
import mediaplayer.yxy.mediaplayer.model.VideoPlayerModel;
import mediaplayer.yxy.mediaplayer.view.ControllerView;
import mediaplayer.yxy.mediaplayer.view.LoadingView;
import mediaplayer.yxy.mediaplayer.view.SimpleSeekBar;
import mediaplayer.yxy.mediaplayer.view.VideoPlayerView;

public class VideoPlayerPresenter {
    private final VideoPlayerView player;
    private final SimpleMediaPlayer simpleMediaPlayer;
    private final ControllerViewPresenter controllerViewPresenter;
    private final LoadingViewPresenter loadingViewPresenter;


    public VideoPlayerPresenter(final VideoPlayerView player) {
        this.player = player;
        this.simpleMediaPlayer = new SimpleMediaPlayer();
        this.controllerViewPresenter = new ControllerViewPresenter(getControllerView());
        this.loadingViewPresenter = new LoadingViewPresenter(createLoadingView());
    }


    public void bind(final VideoPlayerModel model) {
        controllerViewPresenter.bind(new ControllerViewModel(simpleMediaPlayer));
        loadingViewPresenter.bind(new LoadingViewModel(simpleMediaPlayer));

        player.ivFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //初始化player
        MediaParams mediaParams = new MediaParams(
                player.surfaceView.getContext(),
                model.getUrl(),
                model.getHeadData(),
                player.surfaceView);
        mediaParams.setSeekToMs(model.getSeekToMs());
        simpleMediaPlayer.reset(mediaParams);

    }


    public void unbind() {
        loadingViewPresenter.unbind();
        controllerViewPresenter.unbind();
        simpleMediaPlayer.release();
    }

    private LoadingView createLoadingView() {
        return new LoadingView() {
            @Override
            public View getPreparingLoadingView() {
                return player.rlPreparingLoading;
            }

            @Override
            public View getPlayingBufferLoadingView() {
                return player.pbBufferingLoading;
            }
        };
    }

    private ControllerView getControllerView() {
        return new ControllerView() {
            @Override
            public View getControlPanel() {
                return player.llBottomControl;
            }

            @Override
            public View getSurfaceContainer() {
                return player.rlSurfaceContainer;
            }

            @Override
            public SimpleSeekBar getSeekBar() {
                return player.skProgress;
            }

            @Override
            public View getCenterPlayView() {
                return player.ivStart;
            }

            @Override
            public View getCenterPauseView() {
                return player.ivPause;
            }

            @Override
            public TextView getCurrentTimeTextView() {
                return player.tvTimeCurrent;
            }

            @Override
            public TextView getDurationTimeTextView() {
                return player.tvTimeTotal;
            }

            @Override
            public View getControlPanelPlayView() {
                return player.ivStart2;
            }

            @Override
            public View getControlPanelPauseView() {
                return player.ivPause2;
            }
        };
    }
}
