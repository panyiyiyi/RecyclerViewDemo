package com.even.commonrv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.even.commonrv.bean.BaseBindPagerBean

/**
 * Created Even by 2020/10/15
 * 使用databinding实现item备案
 */
open class BaseBindPagerAdapter(private val mItemLists: MutableList<BaseBindPagerBean>) : RecyclerView.Adapter<BaseBindViewHolder>() {
    /**
     * 刷新Item
     */
    fun refreshItem(item: BaseBindPagerBean) {
        if (mItemLists.contains(item)) {
            notifyItemChanged(mItemLists.indexOf(item))
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
        this.mItemLists.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    //移除指定Item
    fun removeItem(item: BaseBindPagerBean) {
        if (mItemLists.contains(item)) {
            removeItem(mItemLists.indexOf(item))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindViewHolder {
        return BaseBindViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun onBindViewHolder(holder: BaseBindViewHolder, position: Int) {
        val bean = mItemLists[position]
        mItemLists[position].cover(holder.bindView)
        if (bean.variable != -1 && bean.getItemData() != null) {
            bind(holder.bindView, bean)
        }
        holder.itemView.setOnClickListener {
            mItemLists[position].onClickListener?.onClickListener()
        }
        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            val onLongClickLister =
                    mItemLists[position].onLongClickLister
            if (onLongClickLister != null) {
                onLongClickLister.onLongClickListener()
                return@OnLongClickListener true
            }
            false
        })
    }

    private fun bind(bindView: ViewDataBinding, bean: BaseBindPagerBean) {
        bindView.setVariable(bean.variable, bean.getItemData())
        bindView.executePendingBindings()
    }

    override fun getItemCount(): Int = mItemLists.size
    override fun getItemViewType(position: Int): Int {
        return mItemLists[position].contentViewId
    }


}