package com.even.recyclerviewdemo.ui;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;

import com.even.commonrv.adapter.BaseMultiLayoutRecyclerAdapter;
import com.even.commonrv.adapter.BaseViewHolder;
import com.even.commonrv.decoration.ItemDecorationWithMargin;
import com.even.commonrv.utils.DisplayUtil;
import com.even.recyclerviewdemo.R;
import com.even.recyclerviewdemo.base.BaseActivity;
import com.even.recyclerviewdemo.beans.MultipleBean;

import java.util.ArrayList;

/**
 * @author by Even on 2019/3/20
 * Emial: emailtopan@163.com
 * 单布局
 */
public class MultipleActivity extends BaseActivity {
    private RecyclerView recyclerView;

    private ArrayList<MultipleBean> dataList = new ArrayList<>();
    BaseMultiLayoutRecyclerAdapter<MultipleBean> multipleAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_multiple;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        findViewById(R.id.tvHaveData).setOnClickListener(clickListener);
        findViewById(R.id.tvNoData).setOnClickListener(clickListener);

        int[] layoutIds = new int[]{R.layout.item_no_data, R.layout.item_single};
        multipleAdapter = new BaseMultiLayoutRecyclerAdapter<MultipleBean>(dataList, layoutIds) {
            @Override
            protected int getItemType(int position) {
                if (TextUtils.isEmpty(dataList.get(position).getValue())) {
                    return 0;
                } else {
                    return 1;
                }
            }

            @Override
            protected void coverts(BaseViewHolder holder, MultipleBean item, int position, int itemType) {
                if (itemType == 0) {
                    holder.setText(R.id.tvRemind, "咋回事小老弟，还没有数据");
                } else {
                    holder.setText(R.id.tvSingle, item.getValue());
                }

            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new ItemDecorationWithMargin().setVerticalMargin((int) DisplayUtil.INSTANCE.dip2px(10)));
        recyclerView.setAdapter(multipleAdapter);

    }

    @Override
    protected void initData() {
        dataList.clear();
        for (int i = 0; i < 20; i++) {
            dataList.add(new MultipleBean("多布局" + i));
        }
        multipleAdapter.notifyDataSetChanged();
    }

    /**
     * 清除数据
     */
    private void noData() {
        dataList.clear();
        dataList.add(new MultipleBean());
        multipleAdapter.notifyDataSetChanged();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tvHaveData:
                    initData();
                    break;

                case R.id.tvNoData:
                    noData();
                    break;

                default:
            }
        }
    };

}
