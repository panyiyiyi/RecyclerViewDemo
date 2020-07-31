package com.even.recyclerviewdemo.beans

import android.content.Context
import com.even.commonrv.adapter.BaseViewHolder
import com.even.commonrv.bean.BaseListPagerBean
import com.even.recyclerviewdemo.R

/**
 * @author Created by Even on 2019/2/21
 * Email: emailtopan@163.com
 * 分类
 */
class ClassifyBean(var textLeft: String) : BaseListPagerBean() {
    override fun cover(
        context: Context,
        holder: BaseViewHolder,
        position: Int
    ) {
        holder.setText(R.id.tvLeft, textLeft)
    }

    override val contentViewId: Int = R.layout.item_classify
}