package com.even.recyclerviewdemo;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.even.commonrv.adapter.BaseListPagerAdapter;
import com.even.commonrv.bean.BaseListPagerBean;
import com.even.commonrv.decoration.ItemDecorationWithMargin;
import com.even.commonrv.impl.OnPagerItemClickListener;
import com.even.recyclerviewdemo.base.BaseActivity;
import com.even.recyclerviewdemo.beans.ClassifyBean;
import com.even.recyclerviewdemo.ui.ComplicateActivity;
import com.even.recyclerviewdemo.ui.DataBindActivity;
import com.even.recyclerviewdemo.ui.DataBindItemActivity;
import com.even.recyclerviewdemo.ui.DecorationActivity;
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
        final ClassifyBean singleLayout = new ClassifyBean("单布局使用");
        singleLayout.setOnClickListener(new OnPagerItemClickListener() {
            @Override
            public void onClickListener() {
                startActivity(SingleActivity.class);
            }
        });
        ClassifyBean multiLayout = new ClassifyBean("多布局使用");
        multiLayout.setOnClickListener(new OnPagerItemClickListener() {
            @Override
            public void onClickListener() {
                startActivity(MultipleActivity.class);
            }
        });
        ClassifyBean stickyLayout = new ClassifyBean("悬浮标题");
        stickyLayout.setOnClickListener(new OnPagerItemClickListener() {
            @Override
            public void onClickListener() {
                startActivity(StickyActivity.class);
            }
        });
        ClassifyBean complicateLayout = new ClassifyBean("常见复杂界面");
        complicateLayout.setOnClickListener(new OnPagerItemClickListener() {
            @Override
            public void onClickListener() {
                startActivity(ComplicateActivity.class);
            }
        });
        ClassifyBean decorationLayout = new ClassifyBean("分割线界面");
        decorationLayout.setOnClickListener(new OnPagerItemClickListener() {
            @Override
            public void onClickListener() {
                startActivity(DecorationActivity.class);
            }
        });
        ClassifyBean bindLayout = new ClassifyBean("DataBinding界面");
        bindLayout.setOnClickListener(new OnPagerItemClickListener() {
            @Override
            public void onClickListener() {
                startActivity(DataBindActivity.class);
            }
        });
        ClassifyBean itemBean = new ClassifyBean("DataBinding ItemBean界面");
        itemBean.setOnClickListener(new OnPagerItemClickListener() {
            @Override
            public void onClickListener() {
                startActivity(DataBindItemActivity.class);
            }
        });

        classifyLists.add(singleLayout);
        classifyLists.add(multiLayout);
        classifyLists.add(stickyLayout);
        classifyLists.add(complicateLayout);
        classifyLists.add(decorationLayout);
        classifyLists.add(bindLayout);
        classifyLists.add(itemBean);


        BaseListPagerAdapter baseListPagerAdapter = new BaseListPagerAdapter(classifyLists);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ItemDecorationWithMargin().setVerticalMargin(10).setHorizontalMargin(10).setDividerHei(1));
        recyclerView.setAdapter(baseListPagerAdapter);
    }
}
