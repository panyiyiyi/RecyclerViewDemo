package com.even.commonrv.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * @author by Even on 2018/11/1
 * Emial: emailtopan@163.com
 * 显示相关工具类
 */
public class DisplayUtil {
    /**
     * dip转换成px
     */
    public static int dip2px(float dipValue) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转dip
     */
    public static int px2dip(float pxValue) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转换成px
     */
    public static int sp2px(float spValue) {
        float scale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * px转换成sp
     */
    public static int px2sp(float pxValue) {
        float scale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getWindowHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * 获取状态栏的高度
     *
     * @param activity
     * @return
     */
    public static int getStateBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

}
