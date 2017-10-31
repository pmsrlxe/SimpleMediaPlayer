package simple.media.player.view.impl;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import simple.media.player.R;
import simple.media.player.view.TouchVolumeView;

/**
 * 音量view
 * Created by rty on 30/10/2017.
 */

public class TouchVolumeViewImpl implements TouchVolumeView {
    private static final int MAX = 100;
    private final Context context;
    private Dialog dlgVolume;
    private ProgressBar dlgVolumeProgressBar;
    private View.OnTouchListener onTouchListener;

    public TouchVolumeViewImpl(Context context) {
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
    public void show(float percent) {
        if (dlgVolume == null) {
            View localView = LayoutInflater.from(context).inflate(R.layout.volume_dialog, null);
            dlgVolumeProgressBar = ((ProgressBar) localView.findViewById(R.id.volume_progressbar));
            dlgVolumeProgressBar.setMax(MAX);
            dlgVolume = new Dialog(context, R.style.oklib_video_player_style_dialog_progress);
            dlgVolume.setContentView(localView);
            dlgVolume.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            dlgVolume.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            dlgVolume.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            dlgVolume.getWindow().setLayout(-2, -2);
            WindowManager.LayoutParams localLayoutParams = dlgVolume.getWindow().getAttributes();
            localLayoutParams.gravity = 19;
            localLayoutParams.x = context.getResources().getDimensionPixelOffset(R.dimen.lib_video_player_volume_dialog_margin_left);
            dlgVolume.getWindow().setAttributes(localLayoutParams);
        }
        if (!dlgVolume.isShowing()) {
            dlgVolume.show();
        }
        dlgVolumeProgressBar.setProgress((int) (100 * percent));
    }

    @Override
    public void dismiss() {
        if (dlgVolume != null) {
            dlgVolume.dismiss();
        }
    }
}
