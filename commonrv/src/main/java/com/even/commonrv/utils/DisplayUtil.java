package com.even.commonrv.utils;

import android.content.res.Resources;

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

}
