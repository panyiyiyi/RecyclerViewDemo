package com.even.recyclerviewdemo.adapter

import com.even.commonrv.adapter.BaseRecyclerAdapter
import com.even.commonrv.adapter.BaseViewHolder
import com.even.recyclerviewdemo.R

/**
 * Created Even by 2020/11/18
 *
 */
class SecondAdapter(list: MutableList<String>) : BaseRecyclerAdapter<String>(list, R.layout.item_single) {
    override fun covert(holder: BaseViewHolder, item: String, position: Int) {
        holder.setText(R.id.tvSingle, item)
    }
}