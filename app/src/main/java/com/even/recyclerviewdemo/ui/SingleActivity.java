package com.even.recyclerviewdemo.ui;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.even.commonrv.adapter.BaseRecyclerAdapter;
import com.even.commonrv.adapter.BaseViewHolder;
import com.even.commonrv.decoration.ItemDecorationWithMargin;
import com.even.commonrv.utils.DisplayUtil;
import com.even.recyclerviewdemo.R;
import com.even.recyclerviewdemo.base.BaseActivity;

import java.util.ArrayList;

/**
 * @author by Even on 2019/3/20
 * Emial: emailtopan@163.com
 * 单布局
 */
public class SingleActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ArrayList<String> dataLists = new ArrayList<>();
    BaseRecyclerAdapter<String> singleAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);

        singleAdapter = new BaseRecyclerAdapter<String>(dataLists, R.layout.item_single) {
            @Override
            protected void covert(BaseViewHolder holder, String item, int position) {
                holder.setText(R.id.tvSingle, item);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new ItemDecorationWithMargin().setVerticalMargin((int) DisplayUtil.INSTANCE.dip2px(10f)));
        recyclerView.setAdapter(singleAdapter);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 20; i++) {
            dataLists.add("单布局" + i);
        }
        singleAdapter.notifyDataSetChanged();
    }
}
