package mediaplayer.yxy.mediaplayer.view;

import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import mediaplayer.yxy.mediaplayer.R;


/**
 * Manage MediaPlayer
 * Created by Yxy
 * On 2016年11月22日16:14:05
 */
public class VideoPlayerView extends FrameLayout {
    public ImageView ivStart;
    public ImageView ivPause;
    public ImageView ivStart2;
    public ImageView ivBack;
    public Button btContinue;
    public SeekBar skProgress;
    public ImageView ivFullScreen;
    public TextView tvTimeCurrent, tvTimeTotal;
    public ViewGroup rlSurfaceContainer;
    public ViewGroup llTopContainer, llBottomControl;
    public SurfaceView surfaceView;
    public RelativeLayout rlNetworkError;
    public RelativeLayout rl4gError;
    public RelativeLayout rlError;
    private Button btNetErrorRefresh;
    private Button btErrorRefresh;
    protected static boolean completioned;
    private boolean prepareing;
    public String url;
    public Object[] objects;
    protected BuriedPointStandard buriedPoint;
    protected int screenWidth;
    protected int screenHeight;
    protected AudioManager mAudioManager;
    protected int threshold = 80;
    protected float downX;
    protected float downY;
    protected boolean changeVolume = false;
    protected boolean changePosition = false;
    protected int downPosition;
    protected int downVolume;
    public Dialog dlgProgress;
    public ProgressBar dlgProgressProgressBar;
    public TextView dlgProgressCurrent;
    public TextView dlgProgressTotal;
    public ImageView dlgProgressIcon;
    protected int resultTimePosition;//change postion when finger up
    public Dialog dlgVolume;
    public ProgressBar dlgVolumeProgressBar;
    protected int duration;
    protected int pausePosition;
    protected int position;

    public ProgressBar pbBottom;
    public TextView tvTitle;
    public RelativeLayout rlThumb;
    public ImageView ivThumb;


    public RelativeLayout rlPreparingLoading;
    public ProgressBar pbBufferingLoading;


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
        ivStart2 = (ImageView) findViewById(R.id.start2);
        ivBack = (ImageView) findViewById(R.id.back);
        rlNetworkError = (RelativeLayout) findViewById(R.id.rl_network_error);
        rl4gError = (RelativeLayout) findViewById(R.id.rl_4g_error);
        rlError = (RelativeLayout) findViewById(R.id.rl_error);
        btNetErrorRefresh = (Button) findViewById(R.id.bt_network_error_refresh);
        btErrorRefresh = (Button) findViewById(R.id.bt_error_refresh);
        btContinue = (Button) findViewById(R.id.bt_continue);
        ivFullScreen = (ImageView) findViewById(R.id.fullscreen);
        skProgress = (SeekBar) findViewById(R.id.progress);
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


//        llBottomControl.setOnClickListener(this);
//        rlSurfaceContainer.setOnClickListener(this);
//        skProgress.setOnTouchListener(this);
//        btContinue.setOnClickListener(this);
//        btErrorRefresh.setOnClickListener(this);
//        btNetErrorRefresh.setOnClickListener(this);

//        rlSurfaceContainer.setOnTouchListener(this);
        screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        screenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
        mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);

    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        float x = event.getX();
//        float y = event.getY();
//        int id = v.getId();
//
//        if (id == R.id.surface_container || id == R.id.thumb) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    requestDisallowInterceptTouchEvent(true);//不允许拦截事件bug:16267
//                    touchingProgressBar = true;
//                    downX = x;
//                    downY = y;
//                    changeVolume = false;
//                    changePosition = false;
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    float deltaX = x - downX;
//                    float deltaY = y - downY;
//                    float absDeltaX = Math.abs(deltaX);
//                    float absDeltaY = Math.abs(deltaY);
//                    if (!changePosition && !changeVolume) {
//                        if (absDeltaX > threshold || absDeltaY > threshold) {
//                            if (absDeltaX >= threshold) {
//                                if (CURRENT_STATE == CURRENT_STATE_PLAYING || CURRENT_STATE == CURRENT_STATE_PAUSE) {
//                                    changePosition = true;
//                                    downPosition = MediaManager.instance().mediaPlayer.getCurrentPosition();
//                                }
//                            } else {
//                                changeVolume = true;
//                                downVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//                            }
//                        }
//                    }
//                    if (changePosition) {
//                        showProgressDialog(deltaX);
//                    }
//                    if (changeVolume) {
//                        showVolumeDialog(-deltaY);
//                    }
//
//                    break;
//                case MotionEvent.ACTION_UP:
//                    requestDisallowInterceptTouchEvent(false);//允许拦截bug:16267
//                    touchingProgressBar = false;
//                    if (dlgProgress != null) {
//                        dlgProgress.dismiss();
//                    }
//                    if (dlgVolume != null) {
//                        dlgVolume.dismiss();
//                    }
//                    if (changePosition) {
//                        MediaPlayer mediaPlayer = MediaManager.instance().mediaPlayer;
//                        if (mediaPlayer != null) {
//                            position = resultTimePosition;
//                            MediaManager.instance().seekTo(position);
//                            int progress = resultTimePosition * 100 / (duration == 0 ? 1 : duration);
//                            skProgress.setProgress(progress);
//                        }
//                    }
//                    startProgressTimer();
//                    break;
//            }
//        } else if (id == R.id.progress) {//if I am seeking bar,no mater whoever can not intercept my event
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    cancelProgressTimer();
//                    ViewParent vpdown = getParent();
//                    while (vpdown != null) {
//                        vpdown.requestDisallowInterceptTouchEvent(true);
//                        vpdown = vpdown.getParent();
//                    }
//                    break;
//                case MotionEvent.ACTION_UP:
//                    startProgressTimer();
//                    ViewParent vpup = getParent();
//                    while (vpup != null) {
//                        vpup.requestDisallowInterceptTouchEvent(false);
//                        vpup = vpup.getParent();
//                    }
//                    break;
//            }
//        }
//
//        return false;
//    }

//    private void showProgressDialog(float deltaX) {
//        if (dlgProgress == null) {
//            LayoutInflater.from(getContext()).inflate(R.layout.progress_dialog, null);
//            View localView = LayoutInflater.from(getContext()).inflate(R.layout.progress_dialog, null);
//            dlgProgressProgressBar = ((ProgressBar) localView.findViewById(R.id.duration_progressbar));
//            dlgProgressCurrent = ((TextView) localView.findViewById(R.id.tv_current));
//            dlgProgressTotal = ((TextView) localView.findViewById(R.id.tv_duration));
//            dlgProgressIcon = ((ImageView) localView.findViewById(R.id.duration_image_tip));
//            dlgProgress = new Dialog(getContext(), R.style.oklib_video_player_style_dialog_progress);
//            dlgProgress.setContentView(localView);
//            dlgProgress.getWindow().addFlags(Window.FEATURE_ACTION_BAR);
//            dlgProgress.getWindow().addFlags(32);
//            dlgProgress.getWindow().addFlags(16);
//            dlgProgress.getWindow().setLayout(-2, -2);
//            WindowManager.LayoutParams localLayoutParams = dlgProgress.getWindow().getAttributes();
//            localLayoutParams.gravity = 49;
//            localLayoutParams.y = getResources().getDimensionPixelOffset(R.dimen.lib_video_player_progress_dialog_margin_top);
//            dlgProgress.getWindow().setAttributes(localLayoutParams);
//        }
//        if (!dlgProgress.isShowing()) {
//            dlgProgress.show();
//        }
//        MediaPlayer mediaPlayer = MediaManager.instance().mediaPlayer;
//        if (mediaPlayer != null) {
//            resultTimePosition = (int) (downPosition + deltaX * duration / screenWidth);
//            if (resultTimePosition > duration) {
//                resultTimePosition = duration;
//            }
//            dlgProgressCurrent.setText(Utils.stringForTime(resultTimePosition));
//            dlgProgressTotal.setText(" / " + Utils.stringForTime(duration) + "");
//            dlgProgressProgressBar.setProgress(resultTimePosition * 100 / duration);
//            if (deltaX > 0) {
//                dlgProgressIcon.setBackgroundResource(R.drawable.forward_icon);
//            } else {
//                dlgProgressIcon.setBackgroundResource(R.drawable.backward_icon);
//            }
//        }
//    }

    private void showVolumeDialog(float deltaY) {
        if (dlgVolume == null) {
            View localView = LayoutInflater.from(getContext()).inflate(R.layout.volume_dialog, null);
            dlgVolumeProgressBar = ((ProgressBar) localView.findViewById(R.id.volume_progressbar));
            dlgVolume = new Dialog(getContext(), R.style.oklib_video_player_style_dialog_progress);
            dlgVolume.setContentView(localView);
            dlgVolume.getWindow().addFlags(8);
            dlgVolume.getWindow().addFlags(32);
            dlgVolume.getWindow().addFlags(16);
            dlgVolume.getWindow().setLayout(-2, -2);
            WindowManager.LayoutParams localLayoutParams = dlgVolume.getWindow().getAttributes();
            localLayoutParams.gravity = 19;
            localLayoutParams.x = getContext().getResources().getDimensionPixelOffset(R.dimen.lib_video_player_volume_dialog_margin_left);
            dlgVolume.getWindow().setAttributes(localLayoutParams);
        }
        if (!dlgVolume.isShowing()) {
            dlgVolume.show();
        }
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int deltaV = (int) (max * deltaY * 3 / screenHeight);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, downVolume + deltaV, 0);
        int transformVolume = (int) (downVolume * 100 / max + deltaY * 3 * 100 / screenHeight);
        dlgVolumeProgressBar.setProgress(transformVolume);


    }


//    protected void setTextAndProgress(int secProgress) {
//        MediaPlayer mediaPlayer = MediaManager.instance().mediaPlayer;
//        if (mediaPlayer != null) {
//            try {
//                position = mediaPlayer.getCurrentPosition();
//                if (position <= 0 && pausePosition > 0) {
//                    //25021 【EPAD2.2_V2.5.8.2】预习：视频播放时拖拽到最后面，在从初始位置往后拖拽到其他位置，时间一直显示00：00
//                    position = pausePosition;
//                }
//                int progress = position * 100 / (duration == 0 ? 1 : duration);
//                setProgressAndTime(progress, secProgress, position, duration);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    protected void setProgressAndTime(int progress, int secProgress, int currentTime, int totalTime) {
//        if (!touchingProgressBar) {
//            if (progress <= 100) skProgress.setProgress(progress);
//        }
        if (secProgress != 0) skProgress.setSecondaryProgress(secProgress);
        tvTimeCurrent.setText(Utils.stringForTime(currentTime));
        tvTimeTotal.setText(Utils.stringForTime(totalTime));
    }

    protected void resetProgressAndTime() {
        skProgress.setProgress(0);
        skProgress.setSecondaryProgress(0);
        tvTimeCurrent.setText(Utils.stringForTime(0));
    }

}
