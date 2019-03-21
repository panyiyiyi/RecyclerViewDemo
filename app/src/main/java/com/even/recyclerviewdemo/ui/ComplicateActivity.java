package com.even.recyclerviewdemo.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.even.commonrv.adapter.BaseListPagerAdapter;
import com.even.commonrv.bean.BaseListPagerBean;
import com.even.commonrv.decoration.ItemDecorationWithMargin;
import com.even.commonrv.impl.OnPagerItemClickListener;
import com.even.commonrv.utils.DisplayUtil;
import com.even.recyclerviewdemo.R;
import com.even.recyclerviewdemo.base.BaseActivity;
import com.even.recyclerviewdemo.beans.ClassifyBean;
import com.even.recyclerviewdemo.beans.InputBean;
import com.even.recyclerviewdemo.beans.PhotoBean;

import java.util.ArrayList;

/**
 * 复杂布局
 */
public class ComplicateActivity extends BaseActivity {
    private RecyclerView recyclerView;
    BaseListPagerAdapter adapter;

    protected ArrayList<BaseListPagerBean> dataLists = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new BaseListPagerAdapter(dataLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new ItemDecorationWithMargin().setMargin(DisplayUtil.dip2px(10)));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        PhotoBean photoBean = new PhotoBean(R.mipmap.ic_launcher, "小老弟");
        ClassifyBean classifyBean = new ClassifyBean("RecyclerDemo", "RecyclerView常用用法封装");
        final InputBean inputBean = new InputBean();
        inputBean.setOnClickListener(new OnPagerItemClickListener() {
            @Override
            public void onClickListener() {
                shortToast(inputBean.getInputText());
            }
        });

        dataLists.add(photoBean);
        dataLists.add(classifyBean);
        dataLists.add(inputBean);


    }
}
