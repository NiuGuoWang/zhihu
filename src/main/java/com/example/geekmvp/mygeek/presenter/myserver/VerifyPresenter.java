package com.example.geekmvp.mygeek.presenter.myserver;

import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.myserver.VerifyBean;
import com.example.geekmvp.mygeek.model.http.myserver.VerifyModel;
import com.example.geekmvp.mygeek.view.myserver.IVerifyView;

public class VerifyPresenter <V extends IVerifyView> extends BasePresenter<V> implements VerifyModel.VerifyFinish {

    private VerifyModel verifyModel = new VerifyModel();

    public void getVerifyData(){
        if(verifyModel != null){
            verifyModel.getVerifyData(this);
        }
    }

    @Override
    public void setContent(VerifyBean info) {
        if(view != null){
            view.showContent(info);
        }
    }

    @Override
    public void showProgress() {
        if(view != null){

        }
    }

    @Override
    public void hideProgress() {
        if(view != null){

        }
    }
}
