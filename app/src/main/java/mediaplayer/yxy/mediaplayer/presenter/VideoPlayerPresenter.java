package mediaplayer.yxy.mediaplayer.presenter;

import android.view.SurfaceHolder;
import android.view.View;
import android.widget.SeekBar;

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
        simpleMediaPlayer.setOnMediaPlayerStateChangeListener(new OnMediaPlayerStateChangeListener() {
            @Override
            public void onStateChange(MediaPlayerState from, MediaPlayerState now) {
                if (now != MediaPlayerState.Started) {
                    player.ivStart.setVisibility(View.VISIBLE);
                    player.ivPause.setVisibility(View.GONE);
                } else {
                    player.ivStart.setVisibility(View.GONE);
                    player.ivPause.setVisibility(View.VISIBLE);
                }
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
                //TODO process不对
                simpleMediaPlayer.seekToPercent(seekBar.getProgress());
            }
        });

        player.surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                MediaParams mediaParams = new MediaParams(
                        player.surfaceView.getContext(),
                        model.getUrl(),
                        model.getHeadData(),
                        player.surfaceView);
                simpleMediaPlayer.reset(mediaParams);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                simpleMediaPlayer.pause();
            }
        });

        player.ivFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    public void unbind() {
        simpleMediaPlayer.release();
    }
}
