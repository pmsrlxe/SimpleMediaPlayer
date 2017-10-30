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
    private int threshold = 80;
    private OnTouchProgressChange onTouchProgressChange;

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

    private class TouchListener implements View.OnTouchListener {

        private float downX;
        private float downY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int viewWidth = v.getMeasuredWidth();
            int viewHeight = v.getMeasuredHeight();
            if (viewWidth == 0 || viewHeight == 0) {
                return false;
            }

            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = x;
                    downY = y;
                    if (onTouchProgressChange != null) {
                        onTouchProgressChange.onTouchDown();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    float deltaX = x - downX;
                    float deltaY = y - downY;
                    float absDeltaX = Math.abs(deltaX);
                    float absDeltaY = Math.abs(deltaY);
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
                        onTouchProgressChange.onTouchUp();
                    }
                    break;
            }
            return false;
        }
    }
}
