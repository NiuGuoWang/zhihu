package com.example.geekmvp.mygeek.ui.zhihu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geekmvp.mygeek.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ZhihuFragment extends Fragment {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    String[] tabList = new String[]{"日报", "专题", "热门"};
    List<Fragment> fragments = new ArrayList<Fragment>();
    Unbinder unbinder;


    public ZhihuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zhihu, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        fragments.clear();
        tabLayout.removeAllTabs();
        fragments.add(new RiBaoFragment());
        fragments.add(new SubjectFragment());
        fragments.add(new HotFragment());
        viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        tabLayout.addTab(tabLayout.newTab().setText(tabList[0]));
        tabLayout.addTab(tabLayout.newTab().setText(tabList[1]));
        tabLayout.addTab(tabLayout.newTab().setText(tabList[2]));
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.getTabAt(0).setText(tabList[0]);
        tabLayout.getTabAt(1).setText(tabList[1]);
        tabLayout.getTabAt(2).setText(tabList[2]);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
