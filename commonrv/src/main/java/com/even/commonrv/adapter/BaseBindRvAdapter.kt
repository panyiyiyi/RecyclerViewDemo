package com.even.commonrv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.even.commonrv.impl.OnItemBindListener
import com.even.commonrv.impl.OnItemLongBindListener

/**
 * Created Even by 2020/10/14
 * 基于DataBinding的Adapter封装
 */
abstract class BaseBindRvAdapter<T> : RecyclerView.Adapter<BaseBindViewHolder<T>> {
    private var mLayoutIds: IntArray
    private var mVariables: IntArray
    var mDataLists: MutableList<T>

    var onItemClick: OnItemBindListener<T>? = null
    var onItemLongClick: OnItemLongBindListener<T>? = null


    constructor(dataLists: MutableList<T>, layoutIds: IntArray, variables: IntArray) {
        this.mDataLists = dataLists
        this.mLayoutIds = layoutIds
        this.mVariables = variables
    }

    constructor(dataLists: MutableList<T>, layoutId: Int, variable: Int) {
        this.mDataLists = dataLists
        this.mLayoutIds = intArrayOf(layoutId)
        this.mVariables = intArrayOf(variable)
    }

    fun insertItem(item: T) {
        mDataLists.add(item)
        notifyItemInserted(itemCount)
    }

    fun insertItem(item: T, position: Int) {
        mDataLists.add(position, item)
        notifyItemInserted(position)
        notifyItemRangeChanged(position, itemCount)
    }

    //移除指定下标Item
    fun removeItem(position: Int) {
        this.mDataLists.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    //移除指定Item
    fun removeItem(item: T) {
        if (mDataLists.contains(item)) {
            removeItem(mDataLists.indexOf(item))
        }
    }

    //刷新指定的item
    fun refreshItem(position: Int) {
        notifyItemChanged(position)
    }

    //更新集合数据
    fun refreshLists(dataLists: MutableList<T>) {
        this.mDataLists = dataLists
        notifyDataSetChanged()
    }

    //添加集合数据
    fun addLists(dataLists: MutableList<T>) {
        this.mDataLists.addAll(dataLists)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindViewHolder<T> {
        return BaseBindViewHolder(
                DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
                        viewType, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseBindViewHolder<T>, position: Int) {
        if (position < itemCount) {
            holder.bind(mVariables[getItemType(position, mDataLists[position])], mDataLists[position])
            covert(holder, mDataLists[position], position)
            setListener(holder, mDataLists[position], position)
        }
    }

    override fun getItemCount() = mDataLists.size

    override fun getItemViewType(position: Int): Int {
        return mLayoutIds[getItemType(position, mDataLists[position])]
    }

    private fun setListener(holder: BaseBindViewHolder<T>, item: T, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClick?.onItemClick(holder, item, position)
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClick?.let {
                it.onItemLongClick(holder, item, position)
                return@setOnLongClickListener true
            }
            return@setOnLongClickListener false
        }
    }

    open fun covert(holder: BaseBindViewHolder<T>, item: T, position: Int) {}

    /**
     *  获取布局类型
     *  @return  与布局集合的下标对应
     */
    abstract fun getItemType(position: Int, item: T): Int
}