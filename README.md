# RecyclerViewDemo
[![Maven](https://maven-badges.herokuapp.com/maven-central/io.github.panyiyiyi/commonrv/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.panyiyiyi/commonrv)

Gradle依赖

        dependencies {
                implementation 'io.github.panyiyiyi:commonrv:3.0.2'
        }
        dependencies {
                implementation 'com.even:commonrv:1.0.7'
        }
        dependencies {
                implementation 'com.even:commonrv:2.2.0'
        }

备注：从3.0.0版本开始，讲arr移植到maven central中了。
从2.0.0版本开始，项目依赖包改成Androidx的，以及使用kotlin进行开发(2.0.1版本改动比较大，请谨慎从旧版更新到新版，不过代码功能未改变)

更新信息：BaseBindRvAdapter 中 getNoDataBean中添加返回当前Item数据，用于灵活配置当前方法返回数据，以级修改GlideUtil中因传入Dialog context引起的闪退问题"

新增：新增通过DataBinding直接绑定View功能，分别的是对应同一数据源的BaseBindRvAdapter,以及对应不同数据源的ItemBean的BaseBindPagerAdapter和BaseBindPagerBean对象.
如果还是需要手动设置的，可以重写Covert方法，在ItemBean中不需要DataBinding的则可以设置variable=-1，以及getItemData=null,具体用法请参考demo

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



