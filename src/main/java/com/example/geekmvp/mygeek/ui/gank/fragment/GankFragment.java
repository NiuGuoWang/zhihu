package com.example.geekmvp.mygeek.ui.gank.fragment;


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
import com.example.geekmvp.mygeek.ui.zhihu.fragment.HotFragment;
import com.example.geekmvp.mygeek.ui.zhihu.fragment.RiBaoFragment;
import com.example.geekmvp.mygeek.ui.zhihu.fragment.SubjectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends Fragment {


    @BindView(R.id.tabLayout_gank)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_gank)
    ViewPager viewpager;
    String[] tabList = new String[]{"ANDROID", "IOS", "前端","福利"};
    List<Fragment> fragments = new ArrayList<Fragment>();
    Unbinder unbinder;

    public GankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gank, container, false);
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
        fragments.add(new AndroidFragment());
        fragments.add(new IOSFragment());
        fragments.add(new FrontFragment());
        fragments.add(new WealFragment());
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
        tabLayout.addTab(tabLayout.newTab().setText(tabList[3]));
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.getTabAt(0).setText(tabList[0]);
        tabLayout.getTabAt(1).setText(tabList[1]);
        tabLayout.getTabAt(2).setText(tabList[2]);
        tabLayout.getTabAt(3).setText(tabList[3]);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
