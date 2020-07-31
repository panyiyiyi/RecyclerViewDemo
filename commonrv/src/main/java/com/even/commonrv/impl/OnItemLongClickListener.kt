package com.even.commonrv.impl

import com.even.commonrv.adapter.BaseViewHolder

/**
 * @author Created by Even on 2019/2/21
 * Email: emailtopan@163.com
 * recyclerView 长按事件
 */
interface OnItemLongClickListener<T> {
    fun onItemLongClick(
        holder: BaseViewHolder,
        item: T,
        position: Int
    )
}