package com.v1ncent.io.gank.utils;

/**
 * 对点击事件进行监听，防止连点
 * Created by v1ncent on 2017/5/8.
 */

public class OnClickUtils {
    private static long lastClickTime;
    private static long forbidClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 两次间隔在多少毫秒
     *
     * @param forbidTime 间隔的时间
     * @return
     */
    public static boolean longTimeForbidClick(long forbidTime) {
        long time = System.currentTimeMillis();
        long timeD = time - forbidClickTime;
        if (0 < timeD && timeD < forbidTime) {
            return true;
        }
        forbidClickTime = time;
        return false;
    }
}
