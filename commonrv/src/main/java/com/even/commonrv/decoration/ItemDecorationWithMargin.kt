package com.even.commonrv.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.even.commonrv.utils.DisplayUtil
import kotlin.math.roundToInt

/**
 * @author Created by Even on 2019/2/21
 * Emial: emailtopan@163.com
 * recyclerView 带margin分割线
 */
class ItemDecorationWithMargin : ItemDecoration {
    private var mDivider: ColorDrawable
    private var mBounds: Rect = Rect()

    private var mMarginTop = DisplayUtil.dip2px(15f).toInt()
    private var mMarginBottom = DisplayUtil.dip2px(15f).toInt()
    private var mMarginLeft = DisplayUtil.dip2px(15f).toInt()
    private var mMarginRight = DisplayUtil.dip2px(15f).toInt()
    private var mDividerHeight = DisplayUtil.dip2px(0.5f).toInt()


    fun setDrawable(drawable: ColorDrawable) {
        mDivider = drawable
    }

    constructor() : this(Color.parseColor("#EEEEEE"))

    constructor(color: Int) {
        mDivider = ColorDrawable()
        mDivider.color = color
    }

    /**
     * 设置垂直方向margin值
     *
     */
    fun setVerticalMargin(dipMarginValue: Int): ItemDecorationWithMargin {
        return setVerticalMargin(dipMarginValue, dipMarginValue)
    }

    /**
     * 设置水平方向Margin
     */
    fun setHorizontalMargin(dipMarginValue: Int): ItemDecorationWithMargin {
        return setHorizontalMargin(dipMarginValue, dipMarginValue)
    }

    /**
     * 设置水平方向margin值
     */
    fun setVerticalMargin(
        dipMarginTopValue: Int,
        dipMarginBottomValue: Int
    ): ItemDecorationWithMargin {
        mMarginTop = DisplayUtil.dip2px(dipMarginTopValue.toFloat()).toInt()
        mMarginBottom = DisplayUtil.dip2px(dipMarginBottomValue.toFloat()).toInt()
        return this
    }

    /**
     * 设置垂直margin值
     *
     */
    fun setHorizontalMargin(
        dipMarginLeftValue: Int,
        dipMarginRightValue: Int
    ): ItemDecorationWithMargin {
        mMarginLeft = DisplayUtil.dip2px(dipMarginLeftValue.toFloat()).toInt()
        mMarginRight = DisplayUtil.dip2px(dipMarginRightValue.toFloat()).toInt()
        return this
    }

    /**
     * 设置分隔线高度
     */
    fun setDividerHei(height: Int): ItemDecorationWithMargin {
        mDividerHeight = DisplayUtil.dip2px(height.toFloat()).toInt()
        return this
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        c.save()
        parent.layoutManager?.let {
            if (it is GridLayoutManager) {
                it.spanCount
                drawGridLayout(c, parent)
            } else {
                it as LinearLayoutManager
                if (it.orientation == LinearLayoutManager.HORIZONTAL) {
                    drawHorizontal(c, parent)
                } else {
                    drawVertical(c, parent)
                }
            }
        }
    }

    /**
     * 绘制网格布局水平线
     */
    private fun drawGridHorizontal(canvas: Canvas, parent: RecyclerView, spanCount: Int) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            if (isLastRow(i, spanCount, childCount)) {
                continue
            }
            val left = if (i % spanCount == 0) {
                //第一个
                child.left + mMarginLeft
            } else {
                child.left
            }
            val right = if (i % spanCount == spanCount - 1) {
                //最右边
                child.right - mMarginRight
            } else {
                child.right
            }
            val top = child.bottom
            val bottom = top + mDividerHeight

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(canvas)
        }
    }

    /**
     * 绘制网络布局垂直线
     */
    private fun drawGridVertical(canvas: Canvas, parent: RecyclerView, spanCount: Int) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            if (i % spanCount == spanCount - 1) {
                continue
            }
            val top = if (i < spanCount) {
                //第一行
                child.top + mMarginTop
            } else {
                child.top
            }
            val remainCount = childCount % spanCount  //余数
            val bottom = if (remainCount == 0) {
                //刚好整行
                if (i >= childCount - spanCount) {
                    child.bottom - mMarginBottom
                } else {
                    child.bottom + mDividerHeight
                }
            } else {
                //不是整行
                if (i >= childCount - remainCount) {
                    child.bottom - mMarginBottom
                } else {
                    child.bottom + mDividerHeight
                }
            }
            val left = child.right
            val right = left + mDividerHeight

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(canvas)

        }

    }

    /**
     * 网格布局时判断是否再最后一行
     */
    private fun isLastRow(position: Int, spanCount: Int, childCount: Int): Boolean {
        val remainCount = childCount % spanCount  //余数
        if (remainCount == 0) {
            //刚好整行
            if (position >= childCount - spanCount) {
                return true
            }
        } else {
            //不是整行
            if (position >= childCount - remainCount) {
                return true
            }
        }
        return false
    }

    /**
     * 绘制GridLayout分割线
     */
    private fun drawGridLayout(canvas: Canvas, parent: RecyclerView) {
        val manager = parent.layoutManager as GridLayoutManager
        val spanCount = manager.spanCount
        drawGridHorizontal(canvas, parent, spanCount)
        drawGridVertical(canvas, parent, spanCount)
        canvas.restore()
    }


    /**
     * 绘制水平
     */
    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop + mMarginTop
            bottom = parent.height - parent.paddingBottom - mMarginBottom
            canvas.clipRect(parent.left, top, parent.right, bottom)
        } else {
            top = mMarginTop
            bottom = mMarginBottom
        }
        val childCount = parent.childCount - 1
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            val right = mBounds.right + child.translationX.roundToInt()
            val left = right - mDividerHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(canvas)
        }
        canvas.restore()
    }


    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft + mMarginLeft
            right = parent.width - parent.paddingRight - mMarginRight
            canvas.clipRect(
                left,
                parent.paddingTop,
                right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = mMarginLeft
            right = parent.width - mMarginRight
        }
        val childCount = parent.childCount - 1
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            val bottom = mBounds.bottom + child.translationY.roundToInt()
            val top = bottom - mDividerHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(canvas)
        }
        canvas.restore()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.layoutManager?.let {
            if (it is GridLayoutManager) {
                outRect.set(0, 0, mDividerHeight, mDividerHeight)
            } else {
                it as LinearLayoutManager
                if (it.orientation == LinearLayoutManager.HORIZONTAL) {
                    outRect.set(0, 0, mDividerHeight, 0)
                } else {
                    outRect.set(0, 0, 0, mDividerHeight)
                }
            }
        }
    }
}