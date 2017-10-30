package simple.media.player.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * 工具类
 * Created by rty on 30/10/2017.
 */

public final class Task {
    private static Handler handler = new Handler(Looper.getMainLooper());

    private Task() {

    }

    public static void submit(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        new Thread(runnable).start();
    }

    public static void post(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        handler.post(runnable);
    }
}
