package mediaplayer.yxy.mediaplayer.view;

import android.view.View;

import java.util.Calendar;

/**
 * Created by yxy on 17/7/25.
 */

public abstract class NoDoubleClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 500;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }

    }

    public abstract void onNoDoubleClick(View v);
}
