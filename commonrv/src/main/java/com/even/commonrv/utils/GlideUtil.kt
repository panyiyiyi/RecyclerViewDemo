package com.even.commonrv.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import java.io.File

/**
 * @author by Even on 2018/11/1
 * Email: emailtopan@163.com
 * 图片加载相关工具类
 */
object GlideUtil {
    /**
     * 加载网络
     */
    fun loadNet(
        target: ImageView,
        url: String,
        options: RequestOptions,
        listener: RequestListener<Drawable?>
    ) {
        if (checkIsDestroyedActivity(target.context as Activity)) {
            return
        }
        getRequestManager(target.context).load(url).listener(listener).apply(options)
            .into(target)
    }

    /**
     * 加载网络
     */
    fun loadNet(
        target: ImageView,
        url: String,
        options: RequestOptions
    ) {
        if (checkIsDestroyedActivity(target.context as Activity)) {
            return
        }
        getRequestManager(target.context).load(url).apply(options).into(target)
    }

    /**
     * 加载网络
     */
    fun loadNet(target: ImageView, url: String?) {
        if (checkIsDestroyedActivity(target.context as Activity)) {
            return
        }
        getRequestManager(target.context).load(url).into(target)
    }

    /**
     * 加载本地资源
     */
    fun loadResource(target: ImageView, resId: Int) {
        if (checkIsDestroyedActivity(target.context as Activity)) {
            return
        }
        getRequestManager(target.context).load(resId).into(target)
    }

    /**
     * 加载文件,带加载监听
     */
    fun loadFile(
        target: ImageView,
        file: File,
        options: RequestOptions,
        listener: RequestListener<Drawable?>
    ) {
        if (checkIsDestroyedActivity(target.context as Activity)) {
            return
        }
        getRequestManager(target.context).load(file).listener(listener)
            .apply(options).into(target)
    }

    /**
     * 加载文件
     */
    fun loadFile(
        target: ImageView,
        file: File,
        options: RequestOptions
    ) {
        if (checkIsDestroyedActivity(target.context as Activity)) {
            return
        }
        getRequestManager(target.context).load(file).apply(options).into(target)
    }

    /**
     * 加载本地文件
     */
    fun loadFile(target: ImageView, file: File) {
        if (checkIsDestroyedActivity(target.context as Activity)) {
            return
        }
        getRequestManager(target.context).load(file).into(target)
    }

    /**
     * 清除内存缓存
     */
    fun clearMemoryCache(context: Context) {
        Glide.get(context).clearMemory()
    }

    /**
     * 清除磁盘缓存
     */
    fun clearDiskCache(context: Context) {
        Glide.get(context).clearDiskCache()
    }

    private fun getRequestManager(context: Context): RequestManager {
        return Glide.with(context)
    }

    /**判断Activity是否已回收，防止You cannot start a load for a destroyed activity错误*/
    fun checkIsDestroyedActivity(activity: Activity): Boolean {
        return activity.isFinishing
    }
}