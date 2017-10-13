package mediaplayer.yxy.mediaplayer;

import android.app.Activity;
import android.os.Bundle;

import mediaplayer.yxy.mediaplayer.model.VideoPlayerModel;
import mediaplayer.yxy.mediaplayer.presenter.VideoPlayerPresenter;
import mediaplayer.yxy.mediaplayer.view.VideoPlayerView;

public class MainActivity extends Activity {
    private static final String url = "http://rv.okjiaoyu.cn/rv_621f116088525ca2fd75e40e16d62a38.h264.mp4";
//    private static final String url = "http://rv.okayshare.cn/rv_SyuhtAb8CA.h264.mp4";
    private VideoPlayerPresenter playerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VideoPlayerView videoPlayerView = new VideoPlayerView(this);

        playerPresenter = new VideoPlayerPresenter(videoPlayerView);
        playerPresenter.bind(new VideoPlayerModel(url, null));

        setContentView(videoPlayerView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playerPresenter.unbind();
    }
}
