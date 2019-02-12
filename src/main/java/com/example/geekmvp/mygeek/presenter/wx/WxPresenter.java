package com.example.geekmvp.mygeek.presenter.wx;

import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.wx.WXItemBean;
import com.example.geekmvp.mygeek.model.http.wx.WxModel;
import com.example.geekmvp.mygeek.view.wx.IWxView;

public class WxPresenter<V extends IWxView> extends BasePresenter<V> implements WxModel.Wxfinish {

    private WxModel wxModel = new WxModel();

    public void getWxData(int page){
        if(wxModel != null){
            wxModel.getWxData(this, page);
        }
    }

    @Override
    public void showContent(WXItemBean info) {
        if(view != null){
            view.showContent(info);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
