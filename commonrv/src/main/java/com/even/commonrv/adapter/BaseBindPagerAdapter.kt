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
class BaseBindPagerAdapter(private val mItemLists: MutableList<BaseBindPagerBean>) : RecyclerView.Adapter<BaseBindViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindViewHolder {
        return BaseBindViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun onBindViewHolder(holder: BaseBindViewHolder, position: Int) {
        mItemLists[position].cover(holder.bindView)
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

    override fun getItemCount(): Int = mItemLists.size
    override fun getItemViewType(position: Int): Int {
        return mItemLists[position].contentViewId
    }


}