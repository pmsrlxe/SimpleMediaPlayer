package test.seek;

import android.app.Activity;
import android.os.Bundle;

import mediaplayer.yxy.mediaplayer.model.VideoPlayerModel;
import mediaplayer.yxy.mediaplayer.presenter.VideoPlayerPresenter;
import mediaplayer.yxy.mediaplayer.view.VideoPlayerView;
import test.TestConst;

/**
 * 测试流程:init-reset-prepared-seekTo
 *
 * 查看播放的时候，是否seekTo到指定的位置了
 */
public class TestSeekTo extends Activity {
    private VideoPlayerPresenter playerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VideoPlayerView videoPlayerView = new VideoPlayerView(this);

        playerPresenter = new VideoPlayerPresenter(videoPlayerView);

        VideoPlayerModel model = new VideoPlayerModel(TestConst.VIDEO_URL, null);
        model.setSeekToMs(50000);

        playerPresenter.bind(model);

        setContentView(videoPlayerView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playerPresenter.unbind();
    }
}
