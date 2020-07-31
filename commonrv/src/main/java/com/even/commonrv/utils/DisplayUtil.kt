package com.even.commonrv.utils

import android.app.Activity
import android.content.res.Resources

/**
 * @author by Even on 2018/11/1
 * Email: emailtopan@163.com
 * 显示相关工具类
 */
object DisplayUtil {
    /**
     * dip转换成px
     */
    fun dip2px(dipValue: Float): Float {
        val scale =
            Resources.getSystem().displayMetrics.density
        return dipValue * scale + 0.5f
    }

    /**
     * px转dip
     */
    fun px2dip(pxValue: Float): Float {
        val scale =
            Resources.getSystem().displayMetrics.density
        return pxValue / scale + 0.5f
    }

    /**
     * sp转换成px
     */
    fun sp2px(spValue: Float): Float {
        val scale =
            Resources.getSystem().displayMetrics.scaledDensity
        return spValue * scale + 0.5f
    }

    /**
     * px转换成sp
     */
    fun px2sp(pxValue: Float): Float {
        val scale =
            Resources.getSystem().displayMetrics.scaledDensity
        return pxValue / scale + 0.5f
    }

    /**
     * 获取屏幕高度
     * @return 屏幕像素高度
     */
    fun getScreenHeight(): Int {
        val displayMetrics = Resources.getSystem().displayMetrics
        return displayMetrics.heightPixels
    }

    /**
     * 获取屏幕宽度
     * @return 屏幕像素宽度
     */
    fun getScreenWidth(): Int {
        val displayMetrics = Resources.getSystem().displayMetrics
        return displayMetrics.widthPixels
    }

    /**
     * 获取状态栏高度
     * @return 返回状态栏像素值
     */
    fun getStatusBarHeight(activity: Activity): Int {
        val identifier = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (identifier > 0) activity.resources.getDimensionPixelSize(identifier) else 60
    }
}