package com.example.geekmvp.mygeek.presenter.gank;

import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;
import com.example.geekmvp.mygeek.model.http.gank.GankModel;
import com.example.geekmvp.mygeek.view.gank.IGankView;

public class GankPresenter<V extends IGankView> extends BasePresenter<V> implements GankModel.GankFinish {

    private GankModel gankModel = new GankModel();

    public void getGankData(String tech, int num, int page){
        if(gankModel != null){
            gankModel.getGankData(this, tech, num, page);
        }
    }

    @Override
    public void setContent(GankItemBean info) {
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
