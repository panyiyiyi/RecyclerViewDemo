package com.even.commonrv.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.even.commonrv.utils.DisplayUtil;

/**
 * @author Created by Even on 2019/2/21
 * Emial: emailtopan@163.com
 * recyclerView 带margin分割线
 */
public class ItemDecorationWithMargin extends RecyclerView.ItemDecoration {
    private ColorDrawable mDivider;
    private final Rect mBounds = new Rect();
    private int MARGINLEFT = DisplayUtil.dip2px(15);
    private int MARGINRIGHT = DisplayUtil.dip2px(15);
    private int mDividerHeight = DisplayUtil.dip2px(0.5F);

    public ItemDecorationWithMargin(int color) {
        mDivider = new ColorDrawable();
        mDivider.setColor(color);
    }

    public ItemDecorationWithMargin() {
        this(Color.parseColor("#EEEEEE"));
    }

    public void setDrawable(ColorDrawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        } else {
            this.mDivider = drawable;
        }
    }

    /**
     * 设置margin值
     *
     * @param dipMarginValue
     * @return ItemDecorationWithMargin
     */
    public ItemDecorationWithMargin setMargin(int dipMarginValue) {
        return setMargin(dipMarginValue, dipMarginValue);
    }

    /**
     * 设置margin值
     *
     * @param dipMarginLeftValue
     * @param dipMarginRightValue
     * @return ItemDecorationWithMargin
     */
    public ItemDecorationWithMargin setMargin(int dipMarginLeftValue, int dipMarginRightValue) {
        MARGINLEFT = DisplayUtil.dip2px(dipMarginLeftValue);
        MARGINRIGHT = DisplayUtil.dip2px(dipMarginRightValue);
        return this;
    }

    /**
     * 设置分隔线高度
     */
    public void setDividerHei(int height) {
        this.mDividerHeight = height;
    }

    @Override
    public void onDraw(Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() != null && this.mDivider != null) {
            this.drawVertical(c, parent);
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int left;
        int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft() + MARGINLEFT;
            right = parent.getWidth() - parent.getPaddingRight() - MARGINRIGHT;
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = MARGINLEFT;
            right = parent.getWidth() - MARGINRIGHT;
        }

        int childCount = parent.getChildCount() - 1;

        for (int i = 0; i < childCount; ++i) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, this.mBounds);
            int bottom = this.mBounds.bottom + Math.round(child.getTranslationY());
            int top = bottom - mDividerHeight;
            this.mDivider.setBounds(left, top, right, bottom);
            this.mDivider.draw(canvas);
        }
        canvas.restore();
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (this.mDivider == null) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set(0, 0, 0, mDividerHeight);
        }
    }
}


