package com.example.geekmvp.mygeek.view.gank;

import com.example.geekmvp.mygeek.base.IView;
import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;

public interface IGankView extends IView {
    void showContent(GankItemBean info);
}
