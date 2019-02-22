package com.even.commonrv.impl;

import com.even.commonrv.adapter.BaseViewHolder;

/**
 * @author Created by Even on 2019/2/21
 * Emial: emailtopan@163.com
 * recyclerView 长按事件
 */
public interface OnItemLongClickListener<T> {
    void onItemLongClick(BaseViewHolder holder, T item, int position);
}
