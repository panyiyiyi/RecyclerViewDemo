package com.even.commonrv.bean;

import com.even.commonrv.adapter.BaseViewHolder;
import com.even.commonrv.impl.OnPagerItemClickListener;
import com.even.commonrv.impl.OnPagerLongItemClickListener;

/**
 * @author Created by Even on 2019/2/21
 * Emial: emailtopan@163.com
 * BaseListPagerAdapter 数据基类对象
 * 所有需要显示Item的 Bean都需要继承该类
 */
public abstract class BaseListPagerBean {
    /**
     * 左侧文字
     */
    protected String textLeft;
    /**
     * 右侧文字
     */
    protected String textRight;
    /**
     * 右侧图标
     */
    protected Integer imageRight;
    /**
     * Item背景颜色
     */
    protected int itemBgColor;

    private OnPagerItemClickListener onClickListener;
    private OnPagerLongItemClickListener onLongClickLister;

    public void setOnClickListener(OnPagerItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickLister(OnPagerLongItemClickListener onLongClickLister) {
        this.onLongClickLister = onLongClickLister;
    }

    public OnPagerItemClickListener getOnClickListener() {
        return onClickListener;
    }

    public OnPagerLongItemClickListener getOnLongClickLister() {
        return onLongClickLister;
    }

    public BaseListPagerBean() {
    }

    public BaseListPagerBean(String textLeft, String textRight) {
        this.textLeft = textLeft;
        this.textRight = textRight;
    }

    public String getTextLeft() {
        return textLeft;
    }

    public void setTextLeft(String textLeft) {
        this.textLeft = textLeft;
    }

    public String getTextRight() {
        return textRight;
    }

    public void setTextRight(String textRight) {
        this.textRight = textRight;
    }

    public Integer getImageRight() {
        return imageRight;
    }

    /**
     * 设置右边图片
     *
     * @param imageRight
     * @return
     */
    public BaseListPagerBean setImageRight(Integer imageRight) {
        this.imageRight = imageRight;
        return this;
    }

    /**
     * 绘制布局
     *
     * @param holder
     * @param position
     */
    public abstract void cover(BaseViewHolder holder, int position);

    /**
     * 获取布局ID
     *
     * @return
     */
    public abstract int getContentViewId();
}
