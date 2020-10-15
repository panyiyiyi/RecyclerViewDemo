package com.even.recyclerviewdemo.ui

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.even.commonrv.adapter.BaseBindRvAdapter
import com.even.commonrv.adapter.BaseBindViewHolder
import com.even.commonrv.decoration.ItemDecorationWithMargin
import com.even.commonrv.impl.OnItemBindListener
import com.even.recyclerviewdemo.BR
import com.even.recyclerviewdemo.R
import com.even.recyclerviewdemo.base.BaseActivity
import com.even.recyclerviewdemo.beans.BindBean
import kotlinx.android.synthetic.main.activity_recyclerview.*

/**
 * Created Even by 2020/10/14
 * databinding 演示界面
 */
class DataBindActivity : BaseActivity() {
    override fun initView() {
        val lists = mutableListOf<BindBean>()
        for (i in 0..19) {
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
        mAdapter.onItemClick = object : OnItemBindListener<BindBean> {
            override fun onItemClick(holder: BaseBindViewHolder<BindBean>, item: BindBean, position: Int) {
                Toast.makeText(this@DataBindActivity, item.title, Toast.LENGTH_LONG).show()
            }
        }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@DataBindActivity)
            addItemDecoration(ItemDecorationWithMargin())
            adapter = mAdapter
        }
    }

    override fun getContentView(): Int = R.layout.activity_recyclerview
}