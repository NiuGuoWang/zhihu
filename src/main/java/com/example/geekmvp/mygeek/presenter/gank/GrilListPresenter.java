package com.example.geekmvp.mygeek.presenter.gank;

import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;
import com.example.geekmvp.mygeek.model.http.gank.GirlListModel;
import com.example.geekmvp.mygeek.view.gank.IGirlListView;

public class GrilListPresenter<V extends IGirlListView> extends BasePresenter<V> implements GirlListModel.GirlListFinish {

    private GirlListModel girlListModel = new GirlListModel();

    public void getGrilListData(int num, int page){
        if(girlListModel != null){
            girlListModel.getRandomGirlData(this, num, page);
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
