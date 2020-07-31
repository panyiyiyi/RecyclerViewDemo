package com.even.commonrv.adapter

/**
 * @author Created by Even on 2019/2/21
 * Email: emailtopan@163.com
 * 多布局Adapter
 */
abstract class BaseMultiLayoutRecyclerAdapter<T>(mDataList: MutableList<T>, layoutIds: IntArray) :
    BaseRecyclerAdapter<T>(mDataList, layoutIds) {

    override fun covert(holder: BaseViewHolder, item: T, position: Int) {
        coverts(holder, item, position, getItemViewType(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItemType(position)
    }

    /**
     * 获取当前View的type，根据type值得不同，加载不同得布局
     */
    protected abstract fun getItemType(position: Int): Int
    protected abstract fun coverts(holder: BaseViewHolder, item: T, position: Int, itemType: Int)
}