package com.even.commonrv.decoration;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Created by Even on 2019/4/22
 * Email: emailtopan@163.com
 */
public class StickyItemDecoration extends RecyclerView.ItemDecoration {
    /**
     * RecyclerView的adapter
     */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    /**
     * RecyclerView的ViewHolder
     */
    private RecyclerView.ViewHolder mViewHolder;
    /**
     * 标题视图
     */
    private View mStickyItemView;
    /**
     * 标题距离顶部的距离
     */
    private int mStickyItemViewMarginTop;
    /**
     * 标题布局高度
     */
    private int mItemViewHeight;
    /**
     * 绑定数据的position
     */
    private int mBindDataPosition = -1;
    /**
     * 布局管理器
     */
    private LinearLayoutManager mLayoutManager;
    /**
     * 这里的ViewType必须和Adapter中的相同，默认为0
     */
    private int mViewType = 0;

    public StickyItemDecoration() {
    }

    public StickyItemDecoration(int mViewType) {
        this.mViewType = mViewType;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //非空判断
        if (parent == null || parent.getAdapter() == null || parent.getAdapter().getItemCount() <= 0) {
            return;
        }
        if (mLayoutManager == null) {
            mLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
        }
        getStickyViewHolder(parent);

        int itemCount = parent.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            View itemView = parent.getChildAt(i);
            //如果是标题
            if ((boolean) itemView.getTag()) {
                if (itemView.getTop() <= 0) {
                    //标题布局全部显示效果
                    bindDataForStickyView(mLayoutManager.findFirstVisibleItemPosition(), parent.getMeasuredWidth());
                } else {
                    bindDataForStickyView(mLayoutManager.findFirstVisibleItemPosition() - 1, parent.getMeasuredWidth());
                }
                if (itemView.getTop() > 0 && itemView.getTop() <= mItemViewHeight) {
                    //处理两个标题重叠绘制效果
                    mStickyItemViewMarginTop = mItemViewHeight - itemView.getTop();
                } else {
                    mStickyItemViewMarginTop = 0;
                    //获取下一个标题
                    View nextStickyView = getNextStickyView(parent);
                    if (nextStickyView != null && nextStickyView.getTop() <= mItemViewHeight) {
                        //若两标题叠在一起了
                        //第二个标题盖住第一个标题多少了
                        mStickyItemViewMarginTop = mItemViewHeight - nextStickyView.getTop();
                    }
                }
                // 准备工作已就绪，开始画出吸附的标题
                drawStickyItemView(c);
                break;
            }
        }

    }

    /**
     * 给StickyView绑定数据
     *
     * @param position
     */
    private void bindDataForStickyView(int position, int width) {
        if (mViewHolder == null) {
            return;
        }
        //已经是吸附位置了 或 视图不存在
        mBindDataPosition = position;
//        //改变标题的展示效果，该方法在Adapter中
        mAdapter.onBindViewHolder(mViewHolder, mBindDataPosition);
        //设置布局位置及大小
        measureLayoutStickyItemView(width);
        //计算标题布局高度
        mItemViewHeight = mViewHolder.itemView.getBottom() - mViewHolder.itemView.getTop();
    }

    /**
     * 设置布局位置及大小
     *
     * @param parentWidth 父布局宽度
     */
    private void measureLayoutStickyItemView(int parentWidth) {
        if (mStickyItemView == null || !mStickyItemView.isLayoutRequested()) {
            return;
        }
        int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        int heightSpec;

        ViewGroup.LayoutParams layoutParams = mStickyItemView.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }

        mStickyItemView.measure(widthSpec, heightSpec);
        /**
         * mContentView.layout(l,t,r,b) ; 子布局相对于父布局的绘制的位置及大小。
         * l 和 t 是控件左边缘和上边缘相对于父类控件左边缘和上边缘的距离。r 和 b是控件右边缘和下边缘相对于父类控件左边缘和上边缘的距离。
         */
        mStickyItemView.layout(0, 0, mStickyItemView.getMeasuredWidth(), mStickyItemView.getMeasuredHeight());
    }

    /**
     * 得到下一个标题
     *
     * @param parent
     * @return
     */
    private View getNextStickyView(RecyclerView parent) {
        int num = 0;
        View nextStickyView = null;
        for (int m = 0, size = parent.getChildCount(); m < size; m++) {
            //循环获取每个子项
            View view = parent.getChildAt(m);
            if ((boolean) view.getTag() == true) {
                //拿到标题
                nextStickyView = view;
                num++;
            }
            //拿到第二个标题 ，就结束循环。
            if (num == 2) {
                break;
            }
        }
        return nextStickyView;
    }

    /**
     * 绘制标题
     *
     * @param canvas
     */
    private void drawStickyItemView(Canvas canvas) {
        if (mStickyItemView == null) {
            return;
        }
        //保存当前图层
        int saveCount = canvas.save();
        //图层转换位移
        canvas.translate(0, -mStickyItemViewMarginTop);
        mStickyItemView.draw(canvas);
        //恢复指定层的图层
        canvas.restoreToCount(saveCount);
    }

    /**
     * 得到标题的 viewHolder
     *
     * @param recyclerView
     */
    private void getStickyViewHolder(RecyclerView recyclerView) {
        //判断是否已创建
        if (mAdapter != null) {
            return;
        }
        mAdapter = recyclerView.getAdapter();
        //该方法属于Adapter中的重写Override
        mViewHolder = mAdapter.onCreateViewHolder(recyclerView, mViewType);
        //得到布局
        mStickyItemView = mViewHolder.itemView;
    }
}