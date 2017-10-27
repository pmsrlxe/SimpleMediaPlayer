package simple.media.player.utils;


import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * 工具类
 */
public class Utils {

    public static boolean isSupportExo() {
        //Exo只支持到16（4.1）以及以上
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isNetworAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnected = false;
        NetworkInfo info = cm.getActiveNetworkInfo();

        if (null != info) {
            isConnected = info.isAvailable();
        }

        return isConnected;
    }

    /**
     * 毫秒转成时分秒
     *
     * @param timeMs
     * @return
     */
    public static String stringForTime(int timeMs) {
        if (timeMs <= 0) {
            return "00:00";
        }
        int totalSeconds = timeMs / 1000;
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    /**
     //     * 设置activity stateBar沉浸
     //     *
     //     * @param activity
     //     */
//    public static void setStateBarTranslucent(Activity activity) {
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && activity != null && !activity.isFinishing()) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    //5.0 translucent
//                    Window window = activity.getWindow();
//                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                    window.setStatusBarColor(activity.getResources().getColor(R.color.lib_state_bar_bg));
//                } else {
//                    //4.0 translucent
//                    Window win = activity.getWindow();
//                    WindowManager.LayoutParams winParams = win.getAttributes();
//                    final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//                    winParams.flags |= bits;
//                    win.setAttributes(winParams);
//
//                    SystemBarTintManager tintManager = new SystemBarTintManager(activity);
//                    tintManager.setStatusBarTintEnabled(true);
//                    tintManager.setStatusBarTintResource(R.color.lib_state_bar_bg);
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * HH:mm
     *
     * @return
     */
    public static String getCurrentTime() {
        Date date = new Date(); // 根据long类型的毫秒数生命一个date类型的时间
        String dateTime = longToDateYMDHMSS(date.getTime());
        return dateTime;
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String longToDateYMDHMSS(long currTime) {
        Date dateOld = new Date(currTime); // 根据long类型的毫秒数生命一个date类型的时间
        String dateTime = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(dateOld);
        return dateTime;
    }

    public static boolean isCurrentActivityShowing(Class clazz, Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        String videoPlayerActivity = clazz.getName();
        return runningActivity.equals(videoPlayerActivity);
    }

    /**
     * 设备所属系列是否是4.x
     *
     * @return
     */
    public static boolean is4S() {
        //规则是: OKAY_产品名称(EBOOK或EPAD)_硬件版本号_OKUI_系统版本号_日期_版本类型

        //取出产品名称,并转换成大写字符
        String ch = Build.DISPLAY.toUpperCase();

        //取出"硬件版本号"的第一个数字
        int d = Integer.parseInt((ch.split("_")[2]).split("\\.")[0]);

        if (ch.contains("EBOOK")) { //ebook
            if (d == 4) {
                return true;
            }
        }

        return false;
    }


}
