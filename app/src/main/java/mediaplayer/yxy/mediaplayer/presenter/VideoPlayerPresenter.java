package mediaplayer.yxy.mediaplayer.presenter;

import android.view.View;
import android.widget.SeekBar;

import mediaplayer.yxy.mediaplayer.OnBufferChangeListener;
import mediaplayer.yxy.mediaplayer.OnBufferStateListener;
import mediaplayer.yxy.mediaplayer.OnMediaPlayerStateChangeListener;
import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.data.MediaParams;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;
import mediaplayer.yxy.mediaplayer.model.VideoPlayerModel;
import mediaplayer.yxy.mediaplayer.view.VideoPlayerView;

public class VideoPlayerPresenter {

    private final VideoPlayerView player;
    private final SimpleMediaPlayer simpleMediaPlayer;

    public VideoPlayerPresenter(final VideoPlayerView player) {
        this.player = player;
        simpleMediaPlayer = new SimpleMediaPlayer();
        //state
        simpleMediaPlayer.setOnMediaPlayerStateChangeListener(new OnMediaPlayerStateChangeListener() {
            @Override
            public void onStateChange(MediaPlayerState from, MediaPlayerState now) {
                if (now != MediaPlayerState.Playing) {
                    player.ivStart.setVisibility(View.VISIBLE);
                    player.ivPause.setVisibility(View.GONE);
                } else {
                    player.ivStart.setVisibility(View.GONE);
                    player.ivPause.setVisibility(View.VISIBLE);
                }

                if (now == MediaPlayerState.Preparing) {
                    player.rlPreparingLoading.setVisibility(View.VISIBLE);
                } else {
                    player.rlPreparingLoading.setVisibility(View.GONE);
                }
            }
        });
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
        simpleMediaPlayer.reset(mediaParams);
    }


    public void unbind() {
        simpleMediaPlayer.release();
    }
}
