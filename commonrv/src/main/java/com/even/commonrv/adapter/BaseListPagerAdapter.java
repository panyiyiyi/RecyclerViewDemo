package com.even.commonrv.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.even.commonrv.bean.BaseListPagerBean;
import com.even.commonrv.impl.OnPagerItemClickListener;
import com.even.commonrv.impl.OnPagerLongItemClickListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Created by Even on 2019/2/21
 * Emial: emailtopan@163.com
 * 用来统一处理列表数据的Adapter，例如：用户个人中心列表显示
 */
public class BaseListPagerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    /**
     * 用于存放ViewType
     */
    Map<String, Integer> mItemTypeMap;
    /**
     * 列表数据集合
     */
    List<BaseListPagerBean> mDataLists;

    public BaseListPagerAdapter(List<BaseListPagerBean> mDataLists) {
        mItemTypeMap = new HashMap<>();
        this.mDataLists = mDataLists;
    }

    /**
     * 刷新数据
     *
     * @param bean
     */
    public void refreshItem(BaseListPagerBean bean) {
        if (mDataLists.contains(bean)) {
            notifyItemChanged(mDataLists.indexOf(bean));
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        return BaseViewHolder.getHolder(viewGroup.getContext(), viewGroup, itemType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        final BaseListPagerBean baseListPagerBean = mDataLists.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnPagerItemClickListener onClickListener = baseListPagerBean.getOnClickListener();
                if (onClickListener != null) {
                    onClickListener.onClickListener();
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                OnPagerLongItemClickListener onLongClickLister = baseListPagerBean.getOnLongClickLister();
                if (onLongClickLister != null) {
                    onLongClickLister.onLongClickListener();
                    return true;
                }
                return false;
            }
        });
        baseListPagerBean.cover(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDataLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        BaseListPagerBean baseListPagerBean = mDataLists.get(position);
        Integer type = mItemTypeMap.get(baseListPagerBean.getClass().getName());
        if (type != null) {
            return type;
        } else {
            mItemTypeMap.put(baseListPagerBean.getClass().getName(), baseListPagerBean.getContentViewId());
            return baseListPagerBean.getContentViewId();
        }
    }
}
