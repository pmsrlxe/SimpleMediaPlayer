package simple.media.player.view.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import simple.media.player.R;


/**
 * 媒体实现
 */
public class VideoPlayerView extends FrameLayout {
    public ImageView ivStart;
    public ImageView ivPause;
    public ImageView ivStart2;
    public ImageView ivBack;
    public Button btContinue;
    public SimpleSeekBar skProgress;
    public ImageView ivFullScreen;
    public TextView tvTimeCurrent, tvTimeTotal;
    public ViewGroup rlSurfaceContainer;
    public ViewGroup llTopContainer, llBottomControl;
    public SurfaceView surfaceView;
    public RelativeLayout rlNetworkError;
    public RelativeLayout rl4gError;
    public RelativeLayout rlError;
    public Button btNetErrorRefresh;
    public Button btErrorRefresh;
    public ProgressBar pbBottom;
    public TextView tvTitle;
    public RelativeLayout rlThumb;
    public ImageView ivThumb;
    public RelativeLayout rlPreparingLoading;
    public ProgressBar pbBufferingLoading;
    public ImageView ivPause2;


    public VideoPlayerView(Context context) {
        super(context);
        init(context);
    }

    public VideoPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    protected void init(Context context) {
        View.inflate(context, R.layout.standard_layout, this);
        ivStart = (ImageView) findViewById(R.id.start);
        ivPause = (ImageView) findViewById(R.id.pause);
        ivPause2 = (ImageView) findViewById(R.id.pause2);
        ivStart2 = (ImageView) findViewById(R.id.start2);
        ivBack = (ImageView) findViewById(R.id.back);
        rlNetworkError = (RelativeLayout) findViewById(R.id.rl_network_error);
        rl4gError = (RelativeLayout) findViewById(R.id.rl_4g_error);
        rlError = (RelativeLayout) findViewById(R.id.rl_error);
        btNetErrorRefresh = (Button) findViewById(R.id.bt_network_error_refresh);
        btErrorRefresh = (Button) findViewById(R.id.bt_error_refresh);
        btContinue = (Button) findViewById(R.id.bt_continue);
        ivFullScreen = (ImageView) findViewById(R.id.fullscreen);
        skProgress = (SimpleSeekBar) findViewById(R.id.progress);
        tvTimeCurrent = (TextView) findViewById(R.id.current);
        tvTimeTotal = (TextView) findViewById(R.id.total);
        llBottomControl = (ViewGroup) findViewById(R.id.layout_bottom);
        rlSurfaceContainer = (RelativeLayout) findViewById(R.id.surface_container);
        llTopContainer = (ViewGroup) findViewById(R.id.layout_top);
        surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);

        pbBottom = (ProgressBar) findViewById(R.id.bottom_progressbar);
        tvTitle = (TextView) findViewById(R.id.title);
        rlThumb = (RelativeLayout) findViewById(R.id.thumb);
        ivThumb = (ImageView) findViewById(R.id.iv_thumb);
        rlPreparingLoading = (RelativeLayout) findViewById(R.id.rl_prepare_loading);
        pbBufferingLoading = (ProgressBar) findViewById(R.id.pb_buffering);
    }
}
