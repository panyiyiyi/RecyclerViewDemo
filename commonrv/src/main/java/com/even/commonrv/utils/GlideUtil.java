package com.even.commonrv.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * @author by Even on 2018/11/1
 * Emial: emailtopan@163.com
 * 显示相关工具类
 */
public class GlideUtil {

    /**
     * 加载网络
     */
    public static void loadNet(ImageView target, String url, RequestOptions options, RequestListener<Drawable> listener) {
        getRequestManager(target.getContext()).load(url).listener(listener).apply(options).into(target);
    }

    /**
     * 加载网络
     */
    public static void loadNet(ImageView target, String url, RequestOptions options) {
        getRequestManager(target.getContext()).load(url).apply(options).into(target);
    }

    /**
     * 加载网络
     */
    public static void loadNet(ImageView target, String url) {
        getRequestManager(target.getContext()).load(url).into(target);
    }

    /**
     * 加载本地资源
     */
    public static void loadResource(ImageView target, int resId) {
        getRequestManager(target.getContext()).load(resId).into(target);
    }

    /**
     * 加载文件,带加载监听
     */
    public static void loadFile(ImageView target, File file, RequestOptions options, RequestListener<Drawable> listener) {
        getRequestManager(target.getContext()).load(file).listener(listener).apply(options).into(target);
    }

    /**
     * 加载文件
     */
    public static void loadFile(ImageView target, File file, RequestOptions options) {
        getRequestManager(target.getContext()).load(file).apply(options).into(target);
    }

    /**
     * 加载本地文件
     */
    public static void loadFile(ImageView target, File file) {
        getRequestManager(target.getContext()).load(file).into(target);
    }

    /**
     * 清除内存缓存
     */
    public static void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 清除磁盘缓存
     */
    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    private static RequestManager getRequestManager(Context context) {
        return Glide.with(context);
    }


}
