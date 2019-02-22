package com.even.recyclerviewdemo.beans;

import com.even.commonrv.adapter.BaseViewHolder;
import com.even.commonrv.bean.BaseListPagerBean;
import com.even.recyclerviewdemo.R;

/**
 * @author Created by Even on 2019/2/21
 * Emial: emailtopan@163.com
 * 分类
 */
public class ClassifyBean extends BaseListPagerBean {

    public ClassifyBean(String textLeft, String textRight) {
        super(textLeft, textRight);
    }

    @Override
    public void cover(BaseViewHolder holder, BaseListPagerBean bean) {
        holder.setText(R.id.tvLeft, textLeft);
    }

    @Override
    public int getContentViewId() {
        return R.layout.item_classify;
    }
}
