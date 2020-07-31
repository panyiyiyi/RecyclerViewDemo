package com.even.commonrv.bean

import android.content.Context
import com.even.commonrv.adapter.BaseViewHolder
import com.even.commonrv.impl.OnPagerItemClickListener
import com.even.commonrv.impl.OnPagerLongItemClickListener

/**
 * @author Created by Even on 2019/2/21
 * Email: emailtopan@163.com
 * BaseListPagerAdapter 数据基类对象
 * 所有需要显示Item的 Bean都需要继承该类
 */
abstract class BaseListPagerBean {
    var onClickListener: OnPagerItemClickListener? = null
    var onLongClickLister: OnPagerLongItemClickListener? = null

    /**
     * 绘制布局
     *
     * @param holder
     * @param position
     */
    abstract fun cover(context: Context, holder: BaseViewHolder, position: Int)

    /**
     * 获取布局ID
     *
     * @return 布局Id
     */
    abstract val contentViewId: Int
}