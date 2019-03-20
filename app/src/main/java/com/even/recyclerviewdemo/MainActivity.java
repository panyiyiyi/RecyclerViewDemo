package com.even.recyclerviewdemo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.even.commonrv.adapter.BaseListPagerAdapter;
import com.even.commonrv.bean.BaseListPagerBean;
import com.even.commonrv.decoration.ItemDecorationWithMargin;
import com.even.commonrv.impl.OnPagerItemClickListener;
import com.even.recyclerviewdemo.base.BaseActivity;
import com.even.recyclerviewdemo.beans.ClassifyBean;
import com.even.recyclerviewdemo.ui.MultipleActivity;
import com.even.recyclerviewdemo.ui.SingleActivity;
import com.even.recyclerviewdemo.ui.StickyActivity;

import java.util.ArrayList;

/**
 * @author by Even on 2019/3/20
 * Emial: emailtopan@163.com
 * 单布局
 */
public class MainActivity extends BaseActivity {
    private RecyclerView recyclerView;

    private ArrayList<BaseListPagerBean> classifyLists = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        final ClassifyBean singleLayout = new ClassifyBean("单布局使用", "");
        singleLayout.setOnClickListener(new OnPagerItemClickListener() {
            @Override
            public void onClickListener() {
                startActivity(SingleActivity.class);
            }
        });
        ClassifyBean multiLayout = new ClassifyBean("多布局使用", "");
        multiLayout.setOnClickListener(new OnPagerItemClickListener() {
            @Override
            public void onClickListener() {
                startActivity(MultipleActivity.class);
            }
        });
        ClassifyBean stickyLayout = new ClassifyBean("悬浮标题", "");
        stickyLayout.setOnClickListener(new OnPagerItemClickListener() {
            @Override
            public void onClickListener() {
                startActivity(StickyActivity.class);
            }
        });

        classifyLists.add(singleLayout);
        classifyLists.add(multiLayout);


        BaseListPagerAdapter baseListPagerAdapter = new BaseListPagerAdapter(classifyLists);
        recyclerView.addItemDecoration(new ItemDecorationWithMargin());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(baseListPagerAdapter);
    }
}
