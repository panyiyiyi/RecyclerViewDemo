package com.even.recyclerviewdemo.beans

import androidx.databinding.ViewDataBinding
import com.even.commonrv.BR
import com.even.commonrv.bean.BaseBindPagerBean

/**
 * Created Even by 2020/10/16
 * ItemBean测试
 */
class ItemFirstBean : BaseBindPagerBean() {
    override fun cover(bindView: ViewDataBinding) {
        bindView.setVariable(BR.title, BindBean("这是大标题", "", true))
        bindView.executePendingBindings()
    }

    override val contentViewId: Int = com.even.recyclerviewdemo.R.layout.item_bind_first_view
}