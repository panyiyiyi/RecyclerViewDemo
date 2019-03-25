package com.even.recyclerviewdemo.beans;

import android.content.Context;
import android.support.annotation.DrawableRes;

import com.even.commonrv.adapter.BaseViewHolder;
import com.even.commonrv.bean.BaseListPagerBean;
import com.even.recyclerviewdemo.R;

public class PhotoBean extends BaseListPagerBean {
    private int photId;
    private String name;

    public PhotoBean(@DrawableRes int photId, String name) {
        this.photId = photId;
        this.name = name;
    }

    @Override
    public void cover(Context context, BaseViewHolder holder, int position) {
        holder.setText(R.id.tvName, name);
        holder.setImageResource(R.id.ivPhoto, photId);
    }

    @Override
    public int getContentViewId() {
        return R.layout.item_photo;
    }
}
