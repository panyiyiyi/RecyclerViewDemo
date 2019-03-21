package com.even.recyclerviewdemo.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.even.commonrv.adapter.BaseViewHolder;
import com.even.commonrv.adapter.MultiLayoutAdapter;
import com.even.commonrv.decoration.StickyItemDecoration;
import com.even.recyclerviewdemo.R;
import com.even.recyclerviewdemo.base.BaseActivity;
import com.even.recyclerviewdemo.beans.StickyBean;

import java.util.ArrayList;

/**
 * @author by Even on 2019/3/20
 * Emial: emailtopan@163.com
 * 悬浮标题
 */
public class StickyActivity extends BaseActivity {
    private RecyclerView recyclerView;

    private ArrayList<StickyBean> dataLists = new ArrayList<>();
    MultiLayoutAdapter<StickyBean> stickyAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);

        int[] layoutIds = new int[]{R.layout.item_sticky_title, R.layout.item_single};
        stickyAdapter = new MultiLayoutAdapter<StickyBean>(dataLists, layoutIds) {
            @Override
            protected int getItemType(int position) {
                return dataLists.get(position).getType();

            }

            @Override
            protected void coverts(BaseViewHolder holder, StickyBean item, int position, int itemType) {
                if (itemType == 0) {
                    holder.setText(R.id.tvTitle, item.getName());
                    holder.itemView.setTag(true);
                } else {
                    holder.setText(R.id.tvSingle, item.getName());
                    holder.itemView.setTag(false);
                }


            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new StickyItemDecoration(0));
        recyclerView.setAdapter(stickyAdapter);
    }

    @Override
    protected void initData() {
        dataLists.clear();
        for (int i = 0; i < 20; i++) {
            if (i % 3 == 0) {
                //3的倍数设置为标题
                dataLists.add(new StickyBean("悬浮标题" + i, 0));
            } else {
                dataLists.add(new StickyBean("悬浮布局" + i, 1));
            }

        }
        stickyAdapter.notifyDataSetChanged();
    }

}
