package mediaplayer.yxy.mediaplayer.presenter;

import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import mediaplayer.yxy.mediaplayer.OnMediaPlayerStateChangeListener;
import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.data.MediaParams;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.listener.OnBufferChangeListener;
import mediaplayer.yxy.mediaplayer.listener.OnBufferStateListener;
import mediaplayer.yxy.mediaplayer.model.ControllerDurationModel;
import mediaplayer.yxy.mediaplayer.model.ControllerViewModel;
import mediaplayer.yxy.mediaplayer.model.ToolBarVisibleModel;
import mediaplayer.yxy.mediaplayer.model.VideoPlayerModel;
import mediaplayer.yxy.mediaplayer.view.ControllerView;
import mediaplayer.yxy.mediaplayer.view.VideoPlayerView;

public class VideoPlayerPresenter {

    private final VideoPlayerView player;
    private final SimpleMediaPlayer simpleMediaPlayer;
    private OnMediaPlayerStateChangeListener onMediaPlayerStateChangeListener;
    private final ControllerViewPresenter controllerViewPresenter;

    public VideoPlayerPresenter(final VideoPlayerView player) {
        this.player = player;
        simpleMediaPlayer = new SimpleMediaPlayer();
        controllerViewPresenter = new ControllerViewPresenter(getControllerView());

        //缓存
        simpleMediaPlayer.setOnBufferChangeListener(new OnBufferChangeListener() {
            @Override
            public void onBufferUpdate(int percent) {
                player.skProgress.setSecondaryProgress(percent);
            }
        });
        //播放中暂停进行缓存
        simpleMediaPlayer.setOnBufferStateListener(new OnBufferStateListener() {
            @Override
            public void onPauseForBufferWhenPlaying() {
                player.pbBufferingLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onResumeFromBufferAndPlay() {
                player.pbBufferingLoading.setVisibility(View.GONE);
            }
        });
    }

    public void bind(final VideoPlayerModel model) {
        //controller
        ControllerViewModel controllerViewModel = new ControllerViewModel();
        controllerViewModel.setToolBarVisibleModel(new ToolBarVisibleModel(simpleMediaPlayer));
        controllerViewModel.setControllerDurationModel(new ControllerDurationModel(simpleMediaPlayer));
        controllerViewPresenter.bind(controllerViewModel);

        //state
        onMediaPlayerStateChangeListener = new OnMediaPlayerStateChangeListener() {
            @Override
            public void onStateChange(MediaPlayerState from, MediaPlayerState now) {
                if (now == MediaPlayerState.Preparing) {
                    player.rlPreparingLoading.setVisibility(View.VISIBLE);
                } else {
                    player.rlPreparingLoading.setVisibility(View.GONE);
                }
            }
        };
        simpleMediaPlayer.addOnMediaPlayerStateChangeListener(onMediaPlayerStateChangeListener);


        player.ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleMediaPlayer.start();
            }
        });
        player.ivStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleMediaPlayer.start();
            }
        });
        player.ivPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleMediaPlayer.pause();
            }
        });

        //seek
        player.skProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                simpleMediaPlayer.seekToPercent(seekBar.getProgress());
            }
        });


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
        if (onMediaPlayerStateChangeListener != null) {
            simpleMediaPlayer.removeOnMediaPlayerStateChangeListener(onMediaPlayerStateChangeListener);
        }
        controllerViewPresenter.unbind();
        simpleMediaPlayer.release();
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
            public SeekBar getSeekBar() {
                return player.skProgress;
            }

            @Override
            public View getCenterStartView() {
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
        };
    }
}
