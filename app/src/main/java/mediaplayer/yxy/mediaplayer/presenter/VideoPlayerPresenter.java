package mediaplayer.yxy.mediaplayer.presenter;

import android.view.SurfaceHolder;
import android.view.View;
import android.widget.SeekBar;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.data.ResetParams;
import mediaplayer.yxy.mediaplayer.model.VideoPlayerModel;
import mediaplayer.yxy.mediaplayer.view.VideoPlayerView;

public class VideoPlayerPresenter {

    private final VideoPlayerView player;
    private final SimpleMediaPlayer simpleMediaPlayer;

    public VideoPlayerPresenter(VideoPlayerView player) {
        this.player = player;
        simpleMediaPlayer = new SimpleMediaPlayer();
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
                simpleMediaPlayer.seek(seekBar.getProgress());
            }
        });

        player.surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                ResetParams resetParams = new ResetParams(model.getUrl(), model.getHeadData(),
                        player.surfaceView);
                simpleMediaPlayer.reset(resetParams);
                
                simpleMediaPlayer.start();
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
}
