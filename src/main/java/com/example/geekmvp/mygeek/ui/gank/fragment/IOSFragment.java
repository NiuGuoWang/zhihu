package com.example.geekmvp.mygeek.ui.gank.fragment;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geekmvp.mygeek.R;
import com.example.geekmvp.mygeek.base.BaseFragment;
import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;
import com.example.geekmvp.mygeek.presenter.gank.GankPresenter;
import com.example.geekmvp.mygeek.ui.gank.activity.GankWebDetailsActivity;
import com.example.geekmvp.mygeek.ui.gank.adapter.GankAdapter;
import com.example.geekmvp.mygeek.view.gank.IGankView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class IOSFragment  extends BaseFragment implements IGankView, GankAdapter.GankItemOnclickListener {


    @BindView(R.id.Recycler_ios)
    RecyclerView recycler;
    Unbinder unbinder;
    private List<GankItemBean.ResultsBean> list;
    private GankAdapter gankAdapter;
    private int page = 1;
    @Override
    protected int getLayout() {
        return R.layout.fragment_io;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new GankPresenter();
    }

    @Override
    protected void initView(View mView) {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        gankAdapter = new GankAdapter(list, mContext, 2);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(gankAdapter);
        gankAdapter.setGankItemOnclickListener(this);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof LinearLayoutManager) {
                        LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
                        int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                        int itemCount = recyclerView.getAdapter().getItemCount();
                        if (lastVisibleItemPosition == itemCount - 1) {
                            page++;
                            initData();
                        }
                    }
                }
            }
        });
    }


    @Override
    protected void initData() {
        ((GankPresenter) presenter).getGankData("iOS", 20, page);
    }

    @Override
    public void showContent(GankItemBean info) {
        List<GankItemBean.ResultsBean> results = info.getResults();
        for (int i = 0; i < results.size(); i++) {
            list.add(results.get(i));
        }
        gankAdapter.notifyDataSetChanged();
    }

    @Override
    public void gankItemOnclick(int position) {
        Intent intent = new Intent(mContext, GankWebDetailsActivity.class);
        intent.putExtra("url", list.get(position).getUrl());
        startActivity(intent);
    }
}
