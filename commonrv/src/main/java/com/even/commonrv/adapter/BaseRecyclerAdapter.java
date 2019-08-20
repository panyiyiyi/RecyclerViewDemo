package com.even.commonrv.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.even.commonrv.impl.OnItemClickListener;
import com.even.commonrv.impl.OnItemLongClickListener;

import java.util.List;

/**
 * @author Created by Even on 2019/2/21
 * Emial: emailtopan@163.com
 * Adapter基类，单布局直接调用改adapter
 * 多布局调用MultiLayoutAdapter布局
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    /**
     * 需要显示的数据
     */
    List<T> mDataList;
    /**
     * 布局Id
     */
    int[] layoutIds;

    OnItemClickListener<T> onItemClick;
    OnItemLongClickListener<T> onItemLongClick;

    public void setOnItemClick(OnItemClickListener<T> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setOnItemLongClick(OnItemLongClickListener<T> onItemLongClick) {
        this.onItemLongClick = onItemLongClick;
    }

    /**
     * 多布局调用
     *
     * @param mDataList
     * @param layoutIds
     */
    public BaseRecyclerAdapter(List<T> mDataList, int[] layoutIds) {
        this.mDataList = mDataList;
        this.layoutIds = layoutIds;
    }

    /**
     * 单布局调用
     *
     * @param mDataList
     * @param layoutId
     */
    public BaseRecyclerAdapter(List<T> mDataList, int layoutId) {
        this.mDataList = mDataList;
        this.layoutIds = new int[]{layoutId};
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return BaseViewHolder.getHolder(viewGroup.getContext(), viewGroup, layoutIds[viewType]);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        if (position < mDataList.size()) {
            setListener(baseViewHolder, mDataList.get(position), position);
            covert(baseViewHolder, mDataList.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    /**
     * 插入条目
     */
    public void insertItem(T itemData, int position) {
        mDataList.add(itemData);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    /**
     * 插入条目
     */
    public void insertItem(T itemData) {
        insertItem(itemData, mDataList.size());
    }

    /**
     * 删除条目
     */
    public void removeItem(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDataList.size());
    }

    /**
     * 删除条目
     */
    public void removeItem(T data) {
        if (mDataList.contains(data)) {
            removeItem(mDataList.indexOf(data));
        }
    }

    public void refreshItem(int position) {
        notifyItemChanged(position);
    }

    /**
     * 刷新条目数据
     */
    public void refreshItemData(T data, int position) {
        mDataList.set(position, data);
        notifyItemChanged(position);
    }

    /**
     * 设置数据
     *
     * @param mDataList
     */
    public void setData(List<T> mDataList) {
        this.mDataList = mDataList;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param mDataList
     */
    public void addData(List<T> mDataList) {
        this.mDataList.addAll(mDataList);
        notifyDataSetChanged();
    }

    /**
     * 设置点击事件
     *
     * @param holder
     * @param item
     * @param position
     */
    private void setListener(final BaseViewHolder holder, final T item, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(holder, item, position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemLongClick != null) {
                    onItemLongClick.onItemLongClick(holder, item, position);
                    return true;
                }
                return false;
            }
        });
    }

    protected abstract void covert(BaseViewHolder holder, T item, int position);
}
