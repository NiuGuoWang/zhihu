package com.example.geekmvp.mygeek.presenter.gank;

import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;
import com.example.geekmvp.mygeek.model.http.gank.RandomGirlModel;
import com.example.geekmvp.mygeek.view.gank.IRandomGirlView;

public class RandomGirlPresenter<V extends IRandomGirlView> extends BasePresenter<V> implements RandomGirlModel.RandomGirlFinish {

    private RandomGirlModel randomGirlModel = new RandomGirlModel();

    public void getRandomGirlData(){
        if(randomGirlModel != null){
            randomGirlModel.getRandomGirlData(this, 1);
        }
    }

    @Override
    public void setContent(GankItemBean info) {
        if(view != null){
            view.showContents(info);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
