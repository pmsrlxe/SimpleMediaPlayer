package simple.media.player.helper;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 为了让touch事件回调能够增加多个监听
 * Created by rty on 30/10/2017.
 */

public class TouchListenersHolder {
    private final List<View.OnTouchListener> onTouchListeners = new ArrayList<>();

    public void addTouchListener(View.OnTouchListener l) {
        if (l == null) {
            return;
        }
        synchronized (onTouchListeners) {
            onTouchListeners.add(l);
        }
    }

    public void removeTouchListener(View.OnTouchListener l) {
        if (l == null) {
            return;
        }
        synchronized (onTouchListeners) {
            onTouchListeners.remove(l);
        }
    }

    public void onTouch(View view, MotionEvent event) {
        synchronized (onTouchListeners) {
            for (View.OnTouchListener l : onTouchListeners) {
                l.onTouch(view, event);
            }
        }
    }
}
