package com.even.recyclerviewdemo.ui

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.even.recyclerviewdemo.R
import com.even.recyclerviewdemo.adapter.FirstAdapter
import com.even.recyclerviewdemo.adapter.SecondAdapter
import com.even.recyclerviewdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_recyclerview.*

/**
 * Created Even by 2020/11/18
 * 合并Adapter类
 */
class ConcatActivity : BaseActivity() {
    private lateinit var mFirstAdapter: FirstAdapter
    private lateinit var mSecondAdapter: SecondAdapter

    private lateinit var mConcatAdapter: ConcatAdapter

    override fun getContentView(): Int = R.layout.activity_recyclerview

    override fun initView() {
        mFirstAdapter = FirstAdapter()
        mSecondAdapter = SecondAdapter(mutableListOf("1", "1", "1", "4", "3"))
        mConcatAdapter = ConcatAdapter(mFirstAdapter, mSecondAdapter)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@ConcatActivity)
            adapter = mConcatAdapter
        }

    }
}