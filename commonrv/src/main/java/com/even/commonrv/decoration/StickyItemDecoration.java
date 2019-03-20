package com.even.commonrv.decoration;

import android.graphics.Canvas;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Even on 2018/11/1
 * Emial: emailtopan@163.com
 */
public class StickyItemDecoration extends RecyclerView.ItemDecoration {
    /**
     * Adapter ：托管数据集合，为每个子项创建视图
     */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    /**
     * 标记：UI滚动过程中是否找到标题
     */
    private boolean mCurrentUIFindStickView;
    /**
     * 标题距离顶部距离
     */
    private int mStickyItemViewMarginTop;
    /**
     * 标题布局高度
     */
    private int mItemViewHeight;
    /**
     * 标题的视图View
     */
    private View mStickyItemView;
    /**
     * 承载子项视图的holder
     */
    private RecyclerView.ViewHolder mViewHolder;
    /**
     * 子项布局管理
     */
    private LinearLayoutManager mLayoutManager;
    /**
     * 绑定数据的position
     */
    private int mBindDataPosition = -1;
    /**
     * 悬浮标题Type
     */
    private int mViewType = 0;

    /**
     * 所有标题的position list
     */
    private List<Integer> mStickyPositionList = new ArrayList<>();

    public StickyItemDecoration() {
    }

    public StickyItemDecoration(int mViewType) {
        this.mViewType = mViewType;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //非空判断
        if (parent.getAdapter().getItemCount() <= 0) return;
        //标记默认不存在小标题
        mCurrentUIFindStickView = false;
        //获取布局管理方式
        mLayoutManager = (LinearLayoutManager) parent.getLayoutManager();

        for (int i = 0, size = parent.getChildCount(); i < size; i++) {
            //viewgroup.getChildCount()：获取所有可见子元素个数。

            //循环得到每一个子项
            View item = parent.getChildAt(i);

            if ((boolean) item.getTag() == true) {
                //判断第几个子项是标题（值在Adapter中设置）
                //标记为true
                mCurrentUIFindStickView = true;
                //得到标题的 viewHolder
                getStickyViewHolder(parent);
                //收集标题的 position
                cacheStickyViewPosition(i);

                if (item.getTop() <= 0) {
                    //标题和父布局的距离。（一般初始化时候先进入）
                    //将第一个可见子项位置 和 父布局宽 传入
                    bindDataForStickyView(mLayoutManager.findFirstVisibleItemPosition(), parent.getMeasuredWidth());
                } else {
                    if (mStickyPositionList.size() > 0) {
                        if (mStickyPositionList.size() == 1) {
                            //若只缓存一个标题
                            bindDataForStickyView(mStickyPositionList.get(0), parent.getMeasuredWidth());
                        } else {
                            //得到标题在RecyclerView中的position
                            int currentPosition = mLayoutManager.findFirstVisibleItemPosition() + i;
                            //根据标题的position获得所在缓存列表中的索引
                            int indexOfCurrentPosition = mStickyPositionList.lastIndexOf(currentPosition);
                            bindDataForStickyView(mStickyPositionList.get(indexOfCurrentPosition - 1), parent.getMeasuredWidth());
                        }
                    }
                }

                if (item.getTop() > 0 && item.getTop() <= mItemViewHeight) {
                    //处理两个标题叠在一起的绘制效果
                    mStickyItemViewMarginTop = mItemViewHeight - item.getTop();
                } else {
                    mStickyItemViewMarginTop = 0;
                    //得到下一个标题view
                    View nextStickyView = getNextStickyView(parent);
                    if (nextStickyView != null && nextStickyView.getTop() <= mItemViewHeight) {
                        //若两标题叠在一起了
                        //第二个标题盖住第一个标题多少了
                        mStickyItemViewMarginTop = mItemViewHeight - nextStickyView.getTop();
                    }
                }

                // 准备工作已就绪，开始画出吸附的标题
                drawStickyItemView(c);

                break;  //结束循环
            }
        }

        if (!mCurrentUIFindStickView) {
            //取反判断（因为它默认值是false）表示：若存在小标题则进入
            mStickyItemViewMarginTop = 0;
            //判断子元素等于item总数并且缓存数大于0
            if (mLayoutManager.findFirstVisibleItemPosition() + parent.getChildCount() == parent.getAdapter().getItemCount() && mStickyPositionList.size() > 0) {
                bindDataForStickyView(mStickyPositionList.get(mStickyPositionList.size() - 1), parent.getMeasuredWidth());
            }
            //绘制图层
            drawStickyItemView(c);
        }

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
            if (num == 2) break;
        }
        return nextStickyView;
    }

    /**
     * 得到标题的 viewHolder
     *
     * @param recyclerView
     */
    private void getStickyViewHolder(RecyclerView recyclerView) {
        //判断是否已创建
        if (mAdapter != null) return;
        mAdapter = recyclerView.getAdapter();
        //该方法属于Adapter中的重写Override
        mViewHolder = mAdapter.onCreateViewHolder(recyclerView, mViewType);

        //得到布局
        mStickyItemView = mViewHolder.itemView;
    }

    /**
     * 收集标题的 position
     *
     * @param i
     */
    private void cacheStickyViewPosition(int i) {
        //得到标题在RecyclerView中的position
        int position = mLayoutManager.findFirstVisibleItemPosition() + i;
        if (!mStickyPositionList.contains(position)) {
            //防止重复
            mStickyPositionList.add(position);
        }
    }

    /**
     * 给StickyView绑定数据
     *
     * @param position
     */
    private void bindDataForStickyView(int position, int width) {
        if (mBindDataPosition == position || mViewHolder == null) return;
        //已经是吸附位置了 或 视图不存在
        mBindDataPosition = position;
        //改变标题的展示效果，该方法在Adapter中
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
        if (mStickyItemView == null || !mStickyItemView.isLayoutRequested()) return;

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
     * 绘制标题
     *
     * @param canvas
     */
    private void drawStickyItemView(Canvas canvas) {
        if (mStickyItemView == null) return;

        //保存当前图层
        int saveCount = canvas.save();
        //图层转换位移
        canvas.translate(0, -mStickyItemViewMarginTop);
        mStickyItemView.draw(canvas);
        //恢复指定层的图层
        canvas.restoreToCount(saveCount);
    }
}
