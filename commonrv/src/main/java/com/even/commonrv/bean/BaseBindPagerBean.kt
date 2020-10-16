package com.even.commonrv.bean

import androidx.databinding.ViewDataBinding
import com.even.commonrv.impl.OnPagerItemClickListener
import com.even.commonrv.impl.OnPagerLongItemClickListener

/**
 * Created Even by 2020/10/15
 *
 */
abstract class BaseBindPagerBean {
    var onClickListener: OnPagerItemClickListener? = null
    var onLongClickLister: OnPagerLongItemClickListener? = null

    abstract fun cover(bindView: ViewDataBinding)

    /**
     * 布局Id
     */
    abstract val contentViewId: Int
}