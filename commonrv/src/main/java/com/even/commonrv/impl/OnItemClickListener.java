package com.even.commonrv.impl;

import com.even.commonrv.adapter.BaseViewHolder;

/**
 * @author Created by Even on 2019/2/21
 * Emial: emailtopan@163.com
 * recyclerView单击事件监听
 */
public interface OnItemClickListener<T> {
    void onItemClick(BaseViewHolder holder, T item, int position);
}
