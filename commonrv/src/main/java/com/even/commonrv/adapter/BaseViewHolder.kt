package com.even.commonrv.adapter

import android.app.Activity
import android.graphics.Bitmap
import android.util.SparseArray
import android.util.TypedValue
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * @author Created by Even on 2019/2/21
 * Email: emailtopan@163.com
 */
class BaseViewHolder(itemView: View) : ViewHolder(itemView) {

    /**
     * 存放View
     */
    private var mItemViews: SparseArray<View?> = SparseArray()

    /**
     * 通过Id获取到view
     *
     * @param viewId
     */
    fun <T : View?> getView(viewId: Int): T? {
        var view = mItemViews[viewId]
        if (view == null) {
            view = itemView.findViewById(viewId)
            mItemViews.put(viewId, view)
        }
        return view as T
    }

    /**
     * textView设置值
     */
    fun setText(viewId: Int, text: CharSequence): BaseViewHolder {
        getView<TextView>(viewId)?.text = text
        return this
    }

    /**
     * 设置文字大小
     */
    fun setTextSize(viewId: Int, textSize: Float): BaseViewHolder {
        getView<TextView>(viewId)?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        return this
    }

    /**
     * 设置text View提示文字
     */
    fun setTextHint(viewId: Int, hintText: String): BaseViewHolder {
        getView<TextView>(viewId)?.hint = hintText
        return this
    }

    /**
     * 设置字符串颜色
     */
    fun setTextColor(viewId: Int, colorId: Int): BaseViewHolder {
        getView<TextView>(viewId)?.setTextColor(colorId)
        return this
    }

    /**
     * 设置View是否被选中
     */
    fun setViewSelected(viewId: Int, isSelected: Boolean): BaseViewHolder {
        getView<View>(viewId)?.isSelected = isSelected
        return this
    }

    /**
     * 设置view的点击事件
     */
    fun setClickListener(viewId: Int, listener: View.OnClickListener): BaseViewHolder {
        getView<View>(viewId)?.setOnClickListener(listener)
        return this
    }

    /**
     * 设置view的长按事件
     */
    fun setLongClickListener(viewId: Int, longClickListener: OnLongClickListener): BaseViewHolder {
        getView<View>(viewId)?.setOnLongClickListener(longClickListener)
        return this
    }

    /**
     * 设置是否可见
     */
    fun setViewVisibility(viewId: Int, visibility: Int): BaseViewHolder {
        getView<View>(viewId)?.visibility = visibility
        return this
    }

    /**
     * 设置View的背景颜色
     */
    fun setViewBackgroundColor(viewId: Int, colorId: Int): BaseViewHolder {
        getView<View>(viewId)?.setBackgroundColor(colorId)
        return this
    }

    /**
     * 设置资源图片
     */
    fun setImageResource(viewId: Int, resId: Int): BaseViewHolder {
        getView<ImageView>(viewId)?.setImageResource(resId)
        return this
    }

    /**
     * 设置View的背景图片
     */
    fun setViewBackgroundResource(viewId: Int, resId: Int): BaseViewHolder {
        getView<View>(viewId)?.setBackgroundResource(resId)
        return this
    }

    /**
     * 设置bitmap图片
     */
    fun setImageBitmap(viewId: Int, bitmap: Bitmap?): BaseViewHolder {
        getView<ImageView>(viewId)?.setImageBitmap(bitmap)
        return this
    }

    /**
     * GroupView添加子View
     */
    fun addChildView(viewId: Int, childView: View): BaseViewHolder {
        getView<ViewGroup>(viewId)?.addView(childView)
        return this
    }

    /**
     * 设置网络图片并显示默认图片
     */
    fun setImageByUrl(viewId: Int, url: String, defaultImage: Int): BaseViewHolder {
        val view = getView<ImageView>(viewId)
        if (!(view?.context as Activity).isFinishing) {
            Glide.with(view.context).load(url).apply(RequestOptions().placeholder(defaultImage))
                .into(view)
        }
        return this
    }

    /**
     * 设置网络图片
     */
    fun setImageByUrl(viewId: Int, url: String): BaseViewHolder {
        val view = getView<ImageView>(viewId)
        if (!(view?.context as Activity).isFinishing) {
            Glide.with(view.context).load(url).into(view)
        }
        return this
    }
}