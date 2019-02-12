package com.example.geekmvp.mygeek.presenter.myserver;

import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.myserver.RegisterBean;
import com.example.geekmvp.mygeek.model.http.myserver.RegisterModel;
import com.example.geekmvp.mygeek.view.myserver.IRegisterView;

import java.util.Map;

public class RegisterPresenter<V extends IRegisterView> extends BasePresenter<V> implements RegisterModel.RegisterFinish {

    private RegisterModel registerModel = new RegisterModel();

    public void getRegisterData(Map<String, Object> map){
        if(registerModel != null){
            registerModel.getRegisterData(this, map);
        }
    }

    @Override
    public void setContent(RegisterBean info) {
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
