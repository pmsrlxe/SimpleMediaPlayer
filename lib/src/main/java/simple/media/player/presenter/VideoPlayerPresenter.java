package simple.media.player.presenter;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import simple.media.player.data.MediaParams;
import simple.media.player.model.ControllerViewModel;
import simple.media.player.model.LoadingViewModel;
import simple.media.player.model.VideoPlayerModel;
import simple.media.player.player.MediaPlayerFactory;
import simple.media.player.player.SimpleMediaPlayer;
import simple.media.player.view.ControllerView;
import simple.media.player.view.LoadingView;
import simple.media.player.view.impl.SimpleSeekBar;
import simple.media.player.view.impl.TouchProgressViewImpl;
import simple.media.player.view.impl.TouchVolumeViewImpl;
import simple.media.player.view.impl.VideoPlayerView;

/**
 * 视频播放的presenter
 * 音频的需要自己实现
 */
public class VideoPlayerPresenter {
    private final VideoPlayerView player;
    private final SimpleMediaPlayer mediaPlayer;
    private final ControllerViewPresenter controllerViewPresenter;
    private final LoadingViewPresenter loadingViewPresenter;
    private final TouchPresenter touchPresenter;//跟屏幕触摸相关的

    public VideoPlayerPresenter(final VideoPlayerView player) {
        this.player = player;
        this.mediaPlayer = MediaPlayerFactory.getMediaPlayer(player.getContext());
        //控制条相关
        this.controllerViewPresenter = new ControllerViewPresenter(getControllerView());
        //loading相关
        this.loadingViewPresenter = new LoadingViewPresenter(createLoadingView());
        //触摸相关（音量、快进）
        Context context = player.getContext();
        this.touchPresenter = new TouchPresenter(player.getContext(), player.rlSurfaceContainer,
                new TouchProgressViewImpl(context), new TouchVolumeViewImpl(context));
    }


    public void bind(final VideoPlayerModel model) {
        controllerViewPresenter.bind(new ControllerViewModel(mediaPlayer));
        loadingViewPresenter.bind(new LoadingViewModel(mediaPlayer));

        player.ivFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        touchPresenter.bind();

        //初始化player
        MediaParams mediaParams = new MediaParams(model.getUrl(),
                model.getHeadData(),
                player.surfaceView);
        mediaParams.setSeekToMs(model.getSeekToMs());
        mediaPlayer.reset(mediaParams);

    }


    public void unbind() {
        loadingViewPresenter.unbind();
        controllerViewPresenter.unbind();
        mediaPlayer.release();
        touchPresenter.release();
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
