package com.even.recyclerviewdemo.beans

import com.even.commonrv.BR
import com.even.commonrv.bean.BaseBindPagerBean

/**
 * Created Even by 2020/10/16
 * ItemBean测试
 */
class ItemFirstBean : BaseBindPagerBean() {

    override val contentViewId: Int = com.even.recyclerviewdemo.R.layout.item_bind_first_view
    override val variable: Int = BR.title

    override fun getItemData(): Any {
        return BindBean("指定", "", false)
    }
}