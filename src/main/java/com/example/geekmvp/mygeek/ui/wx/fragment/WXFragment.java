package com.example.geekmvp.mygeek.ui.wx.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geekmvp.mygeek.R;
import com.example.geekmvp.mygeek.base.BaseFragment;
import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.wx.WXItemBean;
import com.example.geekmvp.mygeek.presenter.wx.WxPresenter;
import com.example.geekmvp.mygeek.ui.wx.WxDetailsActivity;
import com.example.geekmvp.mygeek.ui.wx.adapter.WxAdapter;
import com.example.geekmvp.mygeek.view.wx.IWxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WXFragment extends BaseFragment implements IWxView, WxAdapter.WxItemOnClickListener {


    @BindView(R.id.recyclerview_wx)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private List<WXItemBean.NewslistBean> list;
    private WxAdapter wxAdapter;
    private int page = 1;

    @Override
    protected int getLayout() {
        return R.layout.fragment_wx;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new WxPresenter();
    }

    @Override
    protected void initView(View mView) {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        wxAdapter = new WxAdapter(list, mContext);
        recyclerview.setAdapter(wxAdapter);
        recyclerview.setLayoutManager(linearLayoutManager);
        wxAdapter.setWxItemOnClickListener(this);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        ((WxPresenter)presenter).getWxData(page);
    }


    @Override
    public void showContent(WXItemBean info) {
        List<WXItemBean.NewslistBean> newslist = info.getNewslist();
        for (int i = 0; i < newslist.size(); i++) {
            list.add(newslist.get(i));
        }
        wxAdapter.notifyDataSetChanged();
    }

    @Override
    public void wxItemOnClick(int position) {
        Intent intent = new Intent(mContext, WxDetailsActivity.class);
        intent.putExtra("url", list.get(position).getUrl());
        startActivity(intent);
    }
}
