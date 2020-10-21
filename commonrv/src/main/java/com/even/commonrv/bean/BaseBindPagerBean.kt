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

    open fun cover(bindView: ViewDataBinding) {}

    /**
     * 布局Id
     */
    abstract val contentViewId: Int

    /**
     * 返回绑定生成的ID
     * 当等于-1的时候，表示不使用绑定数据，然后需要对布局操作需要重写cover
     */
    abstract val variable: Int

    /**
     * 设置数据
     *
     * @return 当前返回数据可为null,表示当前布局不使用data绑定数据 ，但是当前布局的根布局还是需要使用layout
     */
    abstract fun getItemData(): Any?
}