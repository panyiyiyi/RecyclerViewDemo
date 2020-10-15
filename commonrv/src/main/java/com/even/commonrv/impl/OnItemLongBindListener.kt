package com.even.commonrv.impl

import com.even.commonrv.adapter.BaseBindViewHolder

/**
 * @author Created by Even on 2019/2/21
 * Email: emailtopan@163.com
 * 使用databinding的Item 长按事件
 */
interface OnItemLongBindListener<T> {
    fun onItemLongClick(
            holder: BaseBindViewHolder<T>,
            item: T,
            position: Int
    )
}