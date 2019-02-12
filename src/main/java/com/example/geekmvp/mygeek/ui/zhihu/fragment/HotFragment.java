package com.example.geekmvp.mygeek.ui.zhihu.fragment;


import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geekmvp.mygeek.R;
import com.example.geekmvp.mygeek.base.BaseFragment;
import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.zhihu.HotListBean;
import com.example.geekmvp.mygeek.presenter.zhihu.HotPresenter;
import com.example.geekmvp.mygeek.ui.zhihu.adapter.HotAdapter;
import com.example.geekmvp.mygeek.view.zhihu.IHotView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment implements IHotView, HotAdapter.OnItemClickListener {

    @BindView(R.id.recyclerview_hot)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private List<HotListBean.RecentBean> list;
    private HotAdapter hotAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new HotPresenter();
    }

    @Override
    protected void initView(View mView) {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        hotAdapter = new HotAdapter(list, mContext);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(hotAdapter);
        hotAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        ((HotPresenter)presenter).getHotData();
    }

    @Override
    public void showContent(HotListBean info) {
        List<HotListBean.RecentBean> recent = info.getRecent();
        for (int i = 0; i < recent.size(); i++) {
            list.add(recent.get(i));
        }
        hotAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemClick(int position) {

    }
}
