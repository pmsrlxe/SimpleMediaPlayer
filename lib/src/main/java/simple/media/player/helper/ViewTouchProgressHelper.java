package simple.media.player.helper;

import android.view.MotionEvent;
import android.view.View;

import simple.media.player.listener.OnTouchProgressChange;
import simple.media.player.view.TouchableView;

/**
 * View触摸帮助
 * Created by rty on 30/10/2017.
 */

public class ViewTouchProgressHelper {
    private final boolean careHorizontalTouch;
    private int threshold = 100;
    private static final int MIN_SCROLL_TIME_GAP = 500;
    private OnTouchProgressChange onTouchProgressChange;
    private boolean enable = true;


    /**
     * @param careHorizontalTouch true只关心水平滑动，false关心垂直触摸
     * @param attachTouchToView   需要触摸的view
     */
    public ViewTouchProgressHelper(boolean careHorizontalTouch, TouchableView attachTouchToView) {
        this.careHorizontalTouch = careHorizontalTouch;
        attachTouchToView.setOnTouchListener(new TouchListener());
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setOnTouchProgressChange(OnTouchProgressChange onTouchProgressChange) {
        this.onTouchProgressChange = onTouchProgressChange;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    private class TouchListener implements View.OnTouchListener {

        private float downX;
        private float downY;
        private long downTime;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int viewWidth = v.getMeasuredWidth();
            int viewHeight = v.getMeasuredHeight();
            if (viewWidth == 0 || viewHeight == 0) {
                return false;
            }

            float x = event.getX();
            float y = event.getY();

            float deltaX = x - downX;
            float deltaY = y - downY;
            float absDeltaX = Math.abs(deltaX);
            float absDeltaY = Math.abs(deltaY);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = x;
                    downY = y;
                    downTime = System.currentTimeMillis();
                    if (onTouchProgressChange != null) {
                        onTouchProgressChange.onTouchDown();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (invalidTouch(absDeltaX, absDeltaY)) {
                        return false;
                    }
                    if (absDeltaX >= threshold) {
                        float pc = deltaX * 1.0f / viewWidth;

                        if (onTouchProgressChange != null && careHorizontalTouch) {
                            onTouchProgressChange.onProgressChange(pc, x > downX);
                            return true;
                        }
                    } else if (absDeltaY > threshold) {
                        float pc = absDeltaY * 1.0f / viewWidth;
                        if (onTouchProgressChange != null && !careHorizontalTouch) {
                            onTouchProgressChange.onProgressChange(pc, y < downY);
                            return true;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (onTouchProgressChange != null) {
                        onTouchProgressChange.onTouchUp(!invalidTouch(absDeltaX, absDeltaY));
                    }
                    break;
            }
            return false;
        }

        private boolean invalidTouch(float absDeltaX, float absDeltaY) {
            if ((System.currentTimeMillis() - downTime) <= MIN_SCROLL_TIME_GAP
                    || (absDeltaX < threshold && absDeltaY < threshold)) {
                return true;
            }
            return false;
        }
    }
}
