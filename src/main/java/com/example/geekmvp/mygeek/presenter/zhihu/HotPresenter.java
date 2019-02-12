package com.example.geekmvp.mygeek.presenter.zhihu;

import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.zhihu.HotListBean;
import com.example.geekmvp.mygeek.model.http.zhihu.HotModel;
import com.example.geekmvp.mygeek.view.zhihu.IHotView;

public class HotPresenter<V extends IHotView> extends BasePresenter<V> implements HotModel.HotFinish {

    private HotModel hotModel = new HotModel();

    public void getHotData(){
        if(hotModel != null){
            hotModel.getHotData(this);
        }
    }

    @Override
    public void setContent(HotListBean info) {
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
