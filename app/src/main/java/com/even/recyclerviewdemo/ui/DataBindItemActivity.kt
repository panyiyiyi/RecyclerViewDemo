package com.even.recyclerviewdemo.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.even.commonrv.adapter.BaseBindPagerAdapter
import com.even.commonrv.bean.BaseBindPagerBean
import com.even.recyclerviewdemo.R
import com.even.recyclerviewdemo.base.BaseActivity
import com.even.recyclerviewdemo.beans.ItemFirstBean
import com.even.recyclerviewdemo.beans.ItemSecondBean
import kotlinx.android.synthetic.main.activity_recyclerview.*

/**
 * Created Even by 2020/10/16
 * 使用itemBean开发项目界面
 */
class DataBindItemActivity : BaseActivity() {
    private var itemLists = mutableListOf<BaseBindPagerBean>()
    override fun initView() {
        itemLists.add(ItemFirstBean())
        itemLists.add(ItemSecondBean())
        val adapter = BaseBindPagerAdapter(itemLists)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@DataBindItemActivity)
            this.adapter = adapter
        }
    }

    override fun getContentView(): Int = R.layout.activity_recyclerview
}