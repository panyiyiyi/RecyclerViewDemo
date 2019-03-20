package com.even.recyclerviewdemo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Created by Even on 2019/3/20
 * Emial: emailtopan@163.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected BaseActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        activity = this;
        initView();
        initData();
    }

    protected void startActivity(Class cls) {
        Intent intent = new Intent(activity, cls);
        startActivity(intent);
    }


    protected abstract int getContentView();

    protected abstract void initView();

    protected void initData() {
    }

}
