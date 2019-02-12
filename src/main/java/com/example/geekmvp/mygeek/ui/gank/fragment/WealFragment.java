package com.example.geekmvp.mygeek.ui.gank.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geekmvp.mygeek.R;
import com.example.geekmvp.mygeek.base.BaseFragment;
import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;
import com.example.geekmvp.mygeek.presenter.gank.GrilListPresenter;
import com.example.geekmvp.mygeek.ui.gank.adapter.WealAdapter;
import com.example.geekmvp.mygeek.view.gank.IGirlListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.http.POST;


/**
 * A simple {@link Fragment} subclass.
 */
public class WealFragment extends BaseFragment implements IGirlListView {

    private List<GankItemBean.ResultsBean> list;
    @BindView(R.id.Recycler_weal)
    RecyclerView recycler;
    Unbinder unbinder;
    private WealAdapter wealAdapter;
    private int page = 1;
    @Override
    protected int getLayout() {
        return R.layout.fragment_weal;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new GrilListPresenter();
    }

    @Override
    protected void initView(View mView) {
        list = new ArrayList<>();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        wealAdapter = new WealAdapter(list, mContext);
        recycler.setAdapter(wealAdapter);
        recycler.setLayoutManager(staggeredGridLayoutManager);
        recycler.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if(layoutManager instanceof StaggeredGridLayoutManager){
                        StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
                        int[] into = new int[2];
                        int[] lastItemPositions = manager.findLastCompletelyVisibleItemPositions(into);
                        int itemCount = recyclerView.getAdapter().getItemCount();
                        for (int i = 0; i < lastItemPositions.length; i++) {
                            int last = lastItemPositions[i];
                            if (last == itemCount-1){
                                page++;
                                initData();
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }

    @Override
    protected void initData() {
        ((GrilListPresenter)presenter).getGrilListData(20, page);
    }

    @Override
    public void showContent(GankItemBean info) {
        List<GankItemBean.ResultsBean> results = info.getResults();
        for (int i = 0; i < results.size(); i++) {
            list.add(results.get(i));
        }
        wealAdapter.notifyDataSetChanged();
    }



}
