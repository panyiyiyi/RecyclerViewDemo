package com.even.recyclerviewdemo.beans

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.even.commonrv.adapter.BaseBindRvAdapter
import com.even.commonrv.bean.BaseBindPagerBean
import com.even.commonrv.decoration.ItemDecorationWithMargin
import com.even.recyclerviewdemo.BR
import com.even.recyclerviewdemo.R
import kotlinx.android.synthetic.main.item_bind_recyclerview.view.*

/**
 * Created Even by 2020/10/21
 * 集合类型
 */
class ItemSecondBean : BaseBindPagerBean() {
    override fun cover(bindView: ViewDataBinding) {

        val lists = mutableListOf<BindBean>()
        for (i in 0..10) {
            lists.add(BindBean("标题${i}", "小标题${i}", i % 3 == 0))
        }

        val mAdapter = object : BaseBindRvAdapter<BindBean>(
                lists,
                intArrayOf(R.layout.item_bind_show, R.layout.item_bind_text2),
                intArrayOf(BR.bean, BR.bind2)
        ) {
            override fun getItemType(position: Int, item: BindBean): Int {
                return if (position % 5 == 0) {
                    1
                } else {
                    0
                }
            }
        }
        with(bindView.root.bindRv) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(ItemDecorationWithMargin())
            adapter = mAdapter
        }

    }

    override val contentViewId: Int = R.layout.item_bind_recyclerview
    override val variable: Int = -1

    override fun getItemData(): Any? {
        return null
    }

}