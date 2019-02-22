package com.even.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.even.commonrv.adapter.BaseListPagerAdapter;
import com.even.commonrv.bean.BaseListPagerBean;
import com.even.commonrv.decoration.ItemDecorationWithMargin;
import com.even.recyclerviewdemo.beans.ClassifyBean;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private ArrayList<BaseListPagerBean> classifyLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        initData();
    }

    private void initData() {
        ClassifyBean singleLayout = new ClassifyBean("单布局使用", "");
        ClassifyBean multiLayout = new ClassifyBean("多布局使用", "");
        classifyLists.add(singleLayout);
        classifyLists.add(multiLayout);


        BaseListPagerAdapter baseListPagerAdapter = new BaseListPagerAdapter(classifyLists);
        recyclerView.addItemDecoration(new ItemDecorationWithMargin());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(baseListPagerAdapter);
    }
}
