package com.even.commonrv.adapter

import android.view.LayoutInflater
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.even.commonrv.bean.BaseListPagerBean

/**
 * @author Created by Even on 2019/2/21
 * Email: emailtopan@163.com
 * 用来统一处理列表数据的Adapter
 */
open class BaseListPagerAdapter(private var mDataLists: List<BaseListPagerBean>) :
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