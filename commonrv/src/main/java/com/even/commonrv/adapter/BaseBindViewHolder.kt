package com.even.commonrv.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created Even by 2020/10/14
 * 使用databinding抽象ViewHolder
 */
class BaseBindViewHolder<T>(val bindView: ViewDataBinding) : RecyclerView.ViewHolder(bindView.root) {
    fun bind(variable: Int, t: T) {
        bindView.setVariable(variable, t)
        bindView.executePendingBindings()
    }
}