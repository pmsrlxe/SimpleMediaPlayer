package simple.media.player.helper;

import android.view.MotionEvent;
import android.view.View;

/**
 * 从touch事件分析click动作
 * Created by rty on 31/10/2017.
 */

public abstract class TouchClickListener implements View.OnTouchListener {
    private static final int MAX_MOVE_X_Y = 30;
    private static final int MAX_CLICK_TIME_MS = 600;
    private long downTime;
    private float downX;
    private float downY;


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downTime = System.currentTimeMillis();
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                long gap = System.currentTimeMillis() - downTime;
                int absX = (int) Math.abs(event.getX() - downX);
                int absY = (int) Math.abs(event.getY() - downY);

                if (gap <= MAX_CLICK_TIME_MS && absX < MAX_MOVE_X_Y && absY < MAX_MOVE_X_Y) {
                    onClick(v);
                }
                break;
        }
        return false;
    }

    public abstract void onClick(View view);
}
