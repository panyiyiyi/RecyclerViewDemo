package com.even.commonrv.impl

import com.even.commonrv.adapter.BaseBindViewHolder

/**
 * Created Even by 2020/10/14
 * 使用databinding的Item 点击事件
 */
interface OnItemBindListener<T> {
    fun onItemClick(
            holder: BaseBindViewHolder,
            item: T,
            position: Int
    )
}