package com.even.recyclerviewdemo.adapter

import com.even.commonrv.adapter.BaseRecyclerAdapter
import com.even.commonrv.adapter.BaseViewHolder
import com.even.recyclerviewdemo.R

/**
 * Created Even by 2020/11/18
 *
 */
class FirstAdapter() : BaseRecyclerAdapter<String>(mutableListOf("1"), R.layout.item_input) {
    override fun covert(holder: BaseViewHolder, item: String, position: Int) {

    }
}