package com.example.geekmvp.mygeek.ui.zhihu.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.geekmvp.mygeek.R;
import com.example.geekmvp.mygeek.base.BaseFragment;
import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.zhihu.DailyBeforeListBean;
import com.example.geekmvp.mygeek.model.bean.zhihu.DailyListBean;
import com.example.geekmvp.mygeek.presenter.zhihu.RiBaoPresenter;
import com.example.geekmvp.mygeek.ui.zhihu.adapter.RibaoAdapter;
import com.example.geekmvp.mygeek.view.zhihu.IRibaoView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class RiBaoFragment extends BaseFragment implements IRibaoView, RibaoAdapter.OnItemClickListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    List<DailyListBean.StoriesBean> list;
    List<String> imgList;
    List<String> titleList;

    Unbinder unbinder;
    private RibaoAdapter ribaoAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_ri_bao;
    }

    //初始化p层
    @Override
    protected BasePresenter createPresenter() {
        return new RiBaoPresenter();
    }

    //初始化界面 可以在此方法里面findViewById
    @Override
    protected void initView(View view) {
        ///view.findViewById();
        imgList = new ArrayList<>();
        titleList = new ArrayList<>();
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        ribaoAdapter = new RibaoAdapter(list, mContext, imgList, titleList);
        recyclerview.setAdapter(ribaoAdapter);
        recyclerview.setLayoutManager(linearLayoutManager);
        ribaoAdapter.setOnItemClickListener(this);
    }

    //初始化数据
    @Override
    protected void initData() {
        ((RiBaoPresenter) presenter).getDailyData();
    }

    //DailyData数据请求成功返回
    @Override
    public void showContent(DailyListBean info) {
        List<DailyListBean.StoriesBean> stories = info.getStories();
        List<DailyListBean.TopStoriesBean> top_stories = info.getTop_stories();
        for (int i = 0; i < top_stories.size(); i++) {
            imgList.add(top_stories.get(i).getImage());
            titleList.add(top_stories.get(i).getTitle());
        }
            list.addAll(stories);
        ribaoAdapter.notifyDataSetChanged();

    }

    @Override
    public void showMoreContent(DailyBeforeListBean dailyBeforeListBean) {

    }

    @Override
    public void OnItemClick(int position) {

    }


}
