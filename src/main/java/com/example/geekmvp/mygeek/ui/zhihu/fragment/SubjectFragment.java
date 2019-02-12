package com.example.geekmvp.mygeek.ui.zhihu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geekmvp.mygeek.R;
import com.example.geekmvp.mygeek.base.BaseFragment;
import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.zhihu.SectionListBean;
import com.example.geekmvp.mygeek.presenter.zhihu.SectionsPresenter;
import com.example.geekmvp.mygeek.ui.zhihu.adapter.SectionsAdapter;
import com.example.geekmvp.mygeek.view.zhihu.ISectionsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends BaseFragment implements ISectionsView, SectionsAdapter.OnItemClickListener {


    @BindView(R.id.recyclerview_sections)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private List<SectionListBean.DataBean> list;
    private SectionsAdapter sectionsAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_subject;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new SectionsPresenter();
    }

    @Override
    protected void initView(View mView) {
        list = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        sectionsAdapter = new SectionsAdapter(list, mContext);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.setAdapter(sectionsAdapter);
        sectionsAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        ((SectionsPresenter)presenter).getSectionsData();
    }

    @Override
    public void showContent(SectionListBean info) {
        List<SectionListBean.DataBean> data = info.getData();
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i));
        }
        sectionsAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemClick(int position) {

    }
}
