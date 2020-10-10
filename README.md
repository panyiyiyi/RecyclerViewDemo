# RecyclerViewDemo

Gradle依赖


        dependencies {
                implementation 'com.even:commonrv:1.0.7'
        }
        dependencies {
                implementation 'com.even:commonrv:2.0.2'
        }

备注：从2.0.0版本开始，项目依赖包改成Androidx的，以及使用kotlin进行开发(2.0.1版本改动比较大，请谨慎从旧版更新到新版，不过代码功能未改变)

新增：添加图片加载工具类GlideUtil，用来加载图片显示

1、使用单布局使用方法


        singleAdapter = new BaseRecyclerAdapter<String>(dataLists, R.layout.item_single) {
            @Override
            protected void covert(BaseViewHolder holder, String item, int position) {
                holder.setText(R.id.tvSingle, item);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new ItemDecorationWithMargin().setMargin(DisplayUtil.dip2px(10)));
        recyclerView.setAdapter(singleAdapter);



2、多布局使用

        int[] layoutIds = new int[]{R.layout.item_no_data, R.layout.item_single};
        MultiLayoutAdapter<MultipleBean> multipleAdapter = new MultiLayoutAdapter<MultipleBean>(dataList, layoutIds) {
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
        recyclerView.addItemDecoration(new ItemDecorationWithMargin().setMargin(DisplayUtil.dip2px(10)));
        recyclerView.setAdapter(multipleAdapter);



3、悬浮标题使用

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



4、普通复杂界面使用



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

!![image](https://github.com/panyiyiyi/RecyclerViewDemo/blob/master/test.gif)



        详细使用请参照demo



