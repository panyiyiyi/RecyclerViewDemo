package com.even.commonrv.adapter;

import java.util.List;

/**
 * @author Created by Even on 2019/2/21
 * Emial: emailtopan@163.com
 * 多布局Adapter
 */
public abstract class MultiLayoutAdapter<T> extends BaseRecyclerAdapter<T> {
    public MultiLayoutAdapter(List mDataList, int[] layoutIds) {
        super(mDataList, layoutIds);
    }

    @Override
    protected void covert(BaseViewHolder holder, T item, int position) {
        coverts(holder, item, position, getItemViewType(position));
    }

    @Override
    public int getItemViewType(int position) {
        return getItemType(position);
    }

    /**
     * 获取当前View的type，根据type值得不同，加载不同得布局
     *
     * @param position
     * @return
     */
    protected abstract int getItemType(int position);

    protected abstract void coverts(BaseViewHolder holder, T item, int position, int itemType);

}
