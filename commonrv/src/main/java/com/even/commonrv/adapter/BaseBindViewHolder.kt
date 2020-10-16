package com.even.commonrv.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created Even by 2020/10/14
 * 使用databinding抽象ViewHolder
 */
class BaseBindViewHolder(val bindView: ViewDataBinding) : RecyclerView.ViewHolder(bindView.root) {
}