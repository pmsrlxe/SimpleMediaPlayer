package simple.media.player.view.impl;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import simple.media.player.R;
import simple.media.player.view.TouchProgressView;

/**
 * 触摸时候的进度条view
 * Created by rty on 30/10/2017.
 */

public class TouchProgressViewImpl implements TouchProgressView {
    private static final int MAX = 100;
    private final Context context;
    private Dialog dlgProgress;
    private ProgressBar dlgProgressProgressBar;
    private TextView dlgProgressCurrent;
    private TextView dlgProgressTotal;
    private ImageView dlgProgressIcon;
    private View.OnTouchListener onTouchListener;

    public TouchProgressViewImpl(Context context) {
        this.context = context;
    }


    @Override
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override
    public View.OnTouchListener getOnTouchListener() {
        return onTouchListener;
    }


    @Override
    public void show(float pc, boolean increase, String timeCurrent, String timeTotal) {
        if (dlgProgress == null) {
            LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
            View localView = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
            dlgProgressProgressBar = ((ProgressBar) localView.findViewById(R.id.duration_progressbar));
            dlgProgressProgressBar.setMax(MAX);
            dlgProgressCurrent = ((TextView) localView.findViewById(R.id.tv_current));
            dlgProgressTotal = ((TextView) localView.findViewById(R.id.tv_duration));
            dlgProgressIcon = ((ImageView) localView.findViewById(R.id.duration_image_tip));
            dlgProgress = new Dialog(context, R.style.oklib_video_player_style_dialog_progress);
            dlgProgress.setContentView(localView);
            dlgProgress.getWindow().addFlags(Window.FEATURE_ACTION_BAR);
            dlgProgress.getWindow().addFlags(32);
            dlgProgress.getWindow().addFlags(16);
            dlgProgress.getWindow().setLayout(-2, -2);
            WindowManager.LayoutParams localLayoutParams = dlgProgress.getWindow().getAttributes();
            localLayoutParams.gravity = 49;
            localLayoutParams.y = context.getResources().getDimensionPixelOffset(R.dimen.lib_video_player_progress_dialog_margin_top);
            dlgProgress.getWindow().setAttributes(localLayoutParams);
        }
        if (!dlgProgress.isShowing()) {
            dlgProgress.show();
        }
        dlgProgressProgressBar.setProgress((int) (pc * MAX));
        dlgProgressCurrent.setText(timeCurrent);
        dlgProgressTotal.setText(timeTotal);

        if (increase) {
            dlgProgressIcon.setBackgroundResource(R.drawable.forward_icon);
        } else {
            dlgProgressIcon.setBackgroundResource(R.drawable.backward_icon);
        }
    }

    @Override
    public void dismiss() {
        if (dlgProgress != null) {
            dlgProgress.dismiss();
        }
    }
}
