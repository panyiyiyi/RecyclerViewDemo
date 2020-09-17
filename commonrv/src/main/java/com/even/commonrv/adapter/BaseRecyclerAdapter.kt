package com.even.commonrv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.even.commonrv.impl.OnItemClickListener
import com.even.commonrv.impl.OnItemLongClickListener

/**
 * @author Created by Even on 2019/2/21
 * Email: emailtopan@163.com
 * Adapter基类，单布局直接调用改adapter
 * 多布局调用MultiLayoutAdapter布局
 */
abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseViewHolder> {
    //需要显示的数据
    var mDataList: MutableList<T>

    // 布局Id
    private var mLayoutIds: IntArray
    var onItemClick: OnItemClickListener<T>? = null
    var onItemLongClick: OnItemLongClickListener<T>? = null


    /**
     * 多布局调用
     *
     * @param mDataList
     * @param layoutIds
     */
    constructor(mDataList: MutableList<T>, layoutIds: IntArray) {
        this.mDataList = mDataList
        this.mLayoutIds = layoutIds
    }

    /**
     * 单布局调用
     *
     * @param mDataList
     * @param layoutId
     */
    constructor(mDataList: MutableList<T>, layoutId: Int) {
        this.mDataList = mDataList
        mLayoutIds = intArrayOf(layoutId)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
                LayoutInflater.from(viewGroup.context).inflate(mLayoutIds[viewType], viewGroup, false)
        )
    }

    override fun onBindViewHolder(baseViewHolder: BaseViewHolder, position: Int) {
        if (position < mDataList.size) {
            setListener(baseViewHolder, mDataList[position], position)
            covert(baseViewHolder, mDataList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    /**
     * 插入条目
     */
    fun insertItem(itemData: T, position: Int) {
        mDataList.add(itemData)
        notifyItemInserted(position)
        notifyItemRangeChanged(position, itemCount)
    }

    /**
     * 删除条目
     */
    fun removeItem(position: Int) {
        mDataList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mDataList.size)
    }

    /**
     * 删除条目
     */
    fun removeItem(data: T) {
        if (mDataList.contains(data)) {
            removeItem(mDataList.indexOf(data))
        }
    }

    fun refreshItem(position: Int) {
        notifyItemChanged(position)
    }

    /**
     * 更新集合数据
     */
    fun refreshLists(dataLists: MutableList<T>) {
        this.mDataList = dataLists
        notifyDataSetChanged()
    }

    /**
     * 刷新条目数据
     */
    fun refreshItemData(data: T, position: Int) {
        mDataList[position] = data
        notifyItemChanged(position)
    }

    /**
     * 添加数据
     *
     * @param dataLists
     */
    fun addData(dataLists: List<T>) {
        mDataList.addAll(dataLists)
        notifyDataSetChanged()
    }

    /**
     * 设置点击事件
     *
     * @param holder
     * @param item
     * @param position
     */
    private fun setListener(holder: BaseViewHolder, item: T, position: Int) {
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

    protected abstract fun covert(holder: BaseViewHolder, item: T, position: Int)
}