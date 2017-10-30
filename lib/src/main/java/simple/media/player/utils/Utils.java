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
    private static boolean testSys = false;

    public static boolean isSupportExo() {
        if (testSys) {
            return false;
        }
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

}
