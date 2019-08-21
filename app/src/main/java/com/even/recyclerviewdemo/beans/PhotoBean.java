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
//        holder.setImageResource(R.id.ivPhoto, photId);
        holder.setImageByUrl(R.id.ivPhoto, "https://wanandroid.com/blogimgs/60462c4c-0d82-41aa-b76d-0406c80fce31.png", photId);
    }

    @Override
    public int getContentViewId() {
        return R.layout.item_photo;
    }
}
