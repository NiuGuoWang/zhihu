package com.example.geekmvp.mygeek.view.wx;

import com.example.geekmvp.mygeek.base.IView;
import com.example.geekmvp.mygeek.model.bean.wx.WXItemBean;

public interface IWxView extends IView {
    void showContent(WXItemBean info);
}
