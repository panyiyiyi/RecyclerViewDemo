package com.even.commonrv.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author Created by Even on 2019/2/21
 * Emial: emailtopan@163.com
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    public View itemView;
    /**
     * 存放View
     */
    SparseArray<View> views;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }

    /**
     * 通过Id获取到view
     *
     * @param viewId
     * @param <T>
     * @return BaseViewHolder
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 返回viewHolder给adapter使用
     *
     * @param context
     * @param parent
     * @param layoutId
     * @param <T>
     * @return BaseViewHolder
     */
    public static <T extends BaseViewHolder> T getHolder(Context context, ViewGroup parent, int layoutId) {
        return (T) new BaseViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    /**
     * textView设置值
     *
     * @param viewId
     * @param text
     * @return BaseViewHolder
     */
    public BaseViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(text);
        }
        return this;
    }

    /**
     * 设置文字大小
     *
     * @param viewId
     * @param textSize
     * @return  BaseViewHolder
     */
    public BaseViewHolder setTextSize(int viewId, int textSize) {
        TextView view = getView(viewId);
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        return this;
    }

    /**
     * 设置text View提示文字
     *
     * @param viewId
     * @param hintText
     * @return BaseViewHolder
     */
    public BaseViewHolder setTextHint(int viewId, String hintText) {
        TextView view = getView(viewId);
        view.setHint(hintText);
        return this;
    }

    /**
     * 设置字符串颜色
     */
    public BaseViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * 设置View是否被选中
     *
     * @param viewId
     * @param isCheck
     * @return BaseViewHolder
     */
    public BaseViewHolder setViewSelected(int viewId, boolean isCheck) {
        View view = getView(viewId);
        view.setSelected(isCheck);
        return this;
    }

    /**
     * 设置view的点击事件
     *
     * @param viewId
     * @param listener
     * @return BaseViewHolder
     */
    public BaseViewHolder setClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置view的长按事件
     *
     * @param viewId
     * @param longClickListener
     * @return BaseViewHolder
     */
    public BaseViewHolder setLongClickListener(int viewId, View.OnLongClickListener longClickListener) {
        View view = getView(viewId);
        view.setOnLongClickListener(longClickListener);
        return this;
    }

    /**
     * 设置是否可见
     *
     * @param viewId
     * @param visibility
     * @return BaseViewHolder
     */
    public BaseViewHolder setViewVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * 设置View的背景颜色
     */
    public BaseViewHolder setViewBgColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(colorId);
        return this;
    }

    /**
     * 设置资源图片
     */
    public BaseViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     * 设置View的背景图片
     *
     * @param viewId
     * @param resId
     * @return BaseViewHolder
     */
    public BaseViewHolder setViewBgResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置bitmap图片
     */
    public BaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 为linearLayout添加子View
     *
     * @param viewId
     * @param childView
     * @return BaseViewHolder
     */
    public BaseViewHolder addChildView(int viewId, View childView) {
        LinearLayout view = getView(viewId);
        view.addView(childView);
        return this;
    }

    /**
     * 设置网络图片并显示默认图片
     *
     * @param viewId
     * @param url
     * @param defaultImage
     * @return BaseViewHolder
     */
    public BaseViewHolder setImageByUrl(int viewId, String url, int defaultImage) {
        ImageView view = getView(viewId);
        if (!((Activity) view.getContext()).isFinishing()) {
            Glide.with(view.getContext()).load(url).apply(new RequestOptions().placeholder(defaultImage)).into(view);
        }
        return this;
    }

}
