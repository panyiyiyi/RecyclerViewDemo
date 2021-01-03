package com.even.commonrv.adapter

import android.view.LayoutInflater
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.even.commonrv.bean.BaseBindPagerBean
import com.even.commonrv.bean.BaseListPagerBean

/**
 * @author Created by Even on 2019/2/21
 * Email: emailtopan@163.com
 * 用来统一处理列表数据的Adapter
 */
open class BaseListPagerAdapter(private val mDataLists: MutableList<BaseListPagerBean>) :
        RecyclerView.Adapter<BaseViewHolder>() {

    /**
     * 刷新数据
     */
    fun refreshItem(bean: BaseListPagerBean) {
        if (mDataLists.contains(bean)) {
            notifyItemChanged(mDataLists.indexOf(bean))
        }
    }

    /**
     * 刷新数据
     */
    fun refreshPosition(position: Int) {
        notifyItemChanged(position)
    }

    //移除指定下标Item
    fun removeItem(position: Int) {
        this.mDataLists.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    //移除指定Item
    fun removeItem(item: BaseBindPagerBean) {
        if (mDataLists.contains(item)) {
            removeItem(mDataLists.indexOf(item))
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): BaseViewHolder {
        return BaseViewHolder(
                LayoutInflater.from(viewGroup.context).inflate(itemType, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val baseListPagerBean = mDataLists[position]
        holder.itemView.setOnClickListener {
            baseListPagerBean.onClickListener?.onClickListener()
        }
        holder.itemView.setOnLongClickListener(OnLongClickListener {
            val onLongClickLister =
                    baseListPagerBean.onLongClickLister
            if (onLongClickLister != null) {
                onLongClickLister.onLongClickListener()
                return@OnLongClickListener true
            }
            false
        })
        baseListPagerBean.cover(holder.itemView.context, holder, position)
    }

    override fun getItemCount(): Int {
        return mDataLists.size
    }

    override fun getItemViewType(position: Int): Int {
        return mDataLists[position].contentViewId
    }

}