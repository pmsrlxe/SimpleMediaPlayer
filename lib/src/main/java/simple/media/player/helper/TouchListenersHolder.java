package simple.media.player.helper;

import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 为了让touch事件回调能够增加多个监听
 * <p>
 * 为什么添加这个类呢？因为触摸会有多个逻辑需要处理
 * 如果各个页面都穿view，逻辑不够内聚，而且touchListener只能设置一次
 * 会互相覆盖。
 * Created by rty on 30/10/2017.
 */

public class TouchListenersHolder {
    private final SparseArray<List<View.OnTouchListener>> onTouchListenerMap = new SparseArray<>();

    private static TouchListenersHolder instance = new TouchListenersHolder();

    private TouchListenersHolder() {

    }

    public static TouchListenersHolder getInstance() {
        return instance;
    }

    public void addTouchListener(View to, View.OnTouchListener l) {
        if (l == null || to == null) {
            return;
        }
        int code = to.hashCode();
        synchronized (onTouchListenerMap) {
            List<View.OnTouchListener> onTouchListeners = onTouchListenerMap.get(code);
            if (onTouchListeners == null) {
                onTouchListeners = new ArrayList<>();
                onTouchListenerMap.put(code, onTouchListeners);
            }
            onTouchListeners.add(l);
        }
        to.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouch(v, event);
                return true;
            }
        });
    }

    public void removeTouchListener(View to, View.OnTouchListener l) {
        if (l == null || to == null) {
            return;
        }
        int code = to.hashCode();
        synchronized (onTouchListenerMap) {
            List<View.OnTouchListener> onTouchListeners = onTouchListenerMap.get(code);
            if (onTouchListeners == null) {
                return;
            }
            onTouchListeners.remove(l);
            if (onTouchListeners.size() == 0) {
                onTouchListenerMap.remove(code);
            }
        }
    }

    private void handleTouch(View view, MotionEvent event) {
        if (view == null) {
            return;
        }
        synchronized (onTouchListenerMap) {
            for (View.OnTouchListener l : onTouchListenerMap.get(view.hashCode())) {
                l.onTouch(view, event);
            }
        }
    }
}
