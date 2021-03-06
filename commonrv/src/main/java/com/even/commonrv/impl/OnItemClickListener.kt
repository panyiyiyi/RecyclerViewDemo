package com.even.commonrv.impl

import com.even.commonrv.adapter.BaseViewHolder

/**
 * @author Created by Even on 2019/2/21
 * Email: emailtopan@163.com
 * recyclerView单击事件监听
 */
interface OnItemClickListener<T> {
    fun onItemClick(
        holder: BaseViewHolder,
        item: T,
        position: Int
    )
}