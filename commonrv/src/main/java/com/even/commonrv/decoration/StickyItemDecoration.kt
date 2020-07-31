package com.even.commonrv.decoration

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * @author Created by Even on 2019/4/22
 * Email: emailtopan@163.com
 */
class StickyItemDecoration : ItemDecoration {
    /**
     * 标题视图
     */
    private var mStickyItemView: View? = null

    private var mViewHolder: RecyclerView.ViewHolder? = null

    /**
     * 标题距离顶部的距离
     */
    private var mStickyItemViewMarginTop = 0

    /**
     * 标题布局高度
     */
    private var mItemViewHeight = 0

    /**
     * 绑定数据的position
     */
    private var mBindDataPosition = -1


    /**
     * 悬浮标题ViewType
     */
    private var mViewType = 0

    constructor(mViewType: Int) {
        this.mViewType = mViewType
    }

    constructor() {}

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        parent.adapter?.let {
            val layoutManager = parent.layoutManager as LinearLayoutManager
            if (it.itemCount > 0) {
                if (mStickyItemView == null) {
                    mViewHolder = it.createViewHolder(parent, mViewType)
                    mStickyItemView = mViewHolder?.itemView
                }
                for (i in 0 until parent.childCount) {
                    val view = parent.getChildAt(i)
                    if (view.tag as Boolean) {
                        //标题
                        if (view.top <= 0) {
                            //标题布局全部显示效果
                            bindDataForStickyView(
                                it,
                                layoutManager.findFirstVisibleItemPosition(),
                                parent.measuredWidth
                            )
                        } else {
                            bindDataForStickyView(
                                it,
                                layoutManager.findFirstVisibleItemPosition() - 1,
                                parent.measuredWidth
                            )
                        }

                        if (view.top in 1..mItemViewHeight) {
                            //两个标题重叠
                            mStickyItemViewMarginTop = mItemViewHeight - view.top
                        } else {
                            mStickyItemViewMarginTop = 0
                            //获取下一个标题
                            val nextStickyView = getNextStickyView(parent)
                            if (nextStickyView != null && nextStickyView.top <= mItemViewHeight) {
                                //若两标题叠在一起了
                                //第二个标题盖住第一个标题多少了
                                mStickyItemViewMarginTop = mItemViewHeight - nextStickyView.top
                            }
                        }
                        drawStickyItemView(c)
                        break
                    }
                }

            }
        }
    }

    /**
     * 给StickyView绑定数据
     */
    private fun bindDataForStickyView(
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
        position: Int,
        width: Int
    ) {
        //已经是吸附位置了 或 视图不存在
        mBindDataPosition = position
        adapter.onBindViewHolder(mViewHolder!!, mBindDataPosition)
        //设置布局位置及大小
        measureLayoutStickyItemView(width)
        //计算标题布局高度
        mItemViewHeight = mStickyItemView!!.bottom - mStickyItemView!!.top
    }

    /**
     * 设置布局位置及大小
     *
     * @param parentWidth 父布局宽度
     */
    private fun measureLayoutStickyItemView(parentWidth: Int) {
        if (mStickyItemView == null || !mStickyItemView!!.isLayoutRequested) {
            return
        }
        val widthSpec = View.MeasureSpec.makeMeasureSpec(
            parentWidth,
            View.MeasureSpec.EXACTLY
        )
        val heightSpec: Int
        val layoutParams = mStickyItemView!!.layoutParams
        heightSpec = if (layoutParams != null && layoutParams.height > 0) {
            View.MeasureSpec.makeMeasureSpec(
                layoutParams.height,
                View.MeasureSpec.EXACTLY
            )
        } else {
            View.MeasureSpec.makeMeasureSpec(
                0,
                View.MeasureSpec.UNSPECIFIED
            )
        }
        mStickyItemView!!.measure(widthSpec, heightSpec)
        mStickyItemView!!.layout(
            0,
            0,
            mStickyItemView!!.measuredWidth,
            mStickyItemView!!.measuredHeight
        )
    }


    /**
     * 得到下一个标题
     */
    private fun getNextStickyView(parent: RecyclerView): View? {
        var num = 0
        var nextStickyView: View? = null
        var m = 0
        while (m < parent.childCount) {
            //循环获取每个子项
            val view = parent.getChildAt(m)
            if (view.tag as Boolean) {
                //拿到标题
                nextStickyView = view
                num++
            }
            //拿到第二个标题 ，就结束循环。
            if (num == 2) {
                break
            }
            m++
        }
        return nextStickyView
    }

    /**
     * 绘制标题
     */
    private fun drawStickyItemView(canvas: Canvas) {
        //保存当前图层
        val saveCount = canvas.save()
        //图层转换位移
        canvas.translate(0f, -mStickyItemViewMarginTop.toFloat())
        mStickyItemView?.draw(canvas)
        //恢复指定层的图层
        canvas.restoreToCount(saveCount)
    }
}