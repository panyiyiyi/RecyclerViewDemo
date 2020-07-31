package com.even.recyclerviewdemo.ui

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.even.commonrv.adapter.BaseRecyclerAdapter
import com.even.commonrv.adapter.BaseViewHolder
import com.even.commonrv.decoration.ItemDecorationWithMargin
import com.even.recyclerviewdemo.R
import com.even.recyclerviewdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_decoration.*

/**
 *  @author  Created by Even on 2020/7/22
 *  Email: emailtopan@163.com
 *  分割线演示类
 */
class DecorationActivity : BaseActivity() {
    override fun initView() {


        val singleAdapter = object : BaseRecyclerAdapter<String>(
            mutableListOf("111", "222", "333", "444", "555"),
            R.layout.item_single
        ) {
            override fun covert(holder: BaseViewHolder, item: String, position: Int) {
                holder.setText(R.id.tvSingle, item)
            }
        }
        rv1.layoutManager = LinearLayoutManager(activity)
        rv1.addItemDecoration(ItemDecorationWithMargin())
        rv1.adapter = singleAdapter

        rv2.layoutManager = GridLayoutManager(activity, 2)
        rv2.addItemDecoration(ItemDecorationWithMargin())
        rv2.adapter = singleAdapter

        rv3.layoutManager = GridLayoutManager(activity, 2)
        rv3.addItemDecoration(
            ItemDecorationWithMargin().setHorizontalMargin(15).setVerticalMargin(10)
                .setDividerHei(5)
        )
        rv3.adapter = singleAdapter

        rv4.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv4.addItemDecoration(
            ItemDecorationWithMargin().setVerticalMargin(10).setDividerHei(1)
        )
        rv4.adapter = singleAdapter

    }

    override fun getContentView(): Int = R.layout.activity_decoration
}