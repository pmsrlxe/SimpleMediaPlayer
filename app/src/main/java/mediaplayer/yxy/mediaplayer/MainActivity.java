package mediaplayer.yxy.mediaplayer;

import android.app.Activity;
import android.os.Bundle;

import mediaplayer.yxy.mediaplayer.model.VideoPlayerModel;
import mediaplayer.yxy.mediaplayer.presenter.VideoPlayerPresenter;
import mediaplayer.yxy.mediaplayer.view.VideoPlayerView;

public class MainActivity extends Activity {
    private static final String url = "http://rv.okayshare.cn/rv_SyuhtAb8CA.h264.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VideoPlayerView videoPlayerView = new VideoPlayerView(this);

        VideoPlayerPresenter playerPresenter = new VideoPlayerPresenter(videoPlayerView);
        playerPresenter.bind(new VideoPlayerModel(url, null));

        setContentView(videoPlayerView);

    }
}
