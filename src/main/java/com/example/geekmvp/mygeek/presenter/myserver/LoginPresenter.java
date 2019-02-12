package com.example.geekmvp.mygeek.presenter.myserver;

import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.myserver.LoginBean;
import com.example.geekmvp.mygeek.model.http.myserver.LoginModel;
import com.example.geekmvp.mygeek.view.myserver.ILoginView;

import java.util.Map;

public class LoginPresenter<V extends ILoginView> extends BasePresenter<V> implements LoginModel.LoginFinish {

    private LoginModel loginModel = new LoginModel();

    public void getLoginData(Map<String, Object> map){
        if(loginModel != null){
            loginModel.getLoginData(this, map);
        }
    }

    @Override
    public void setContent(LoginBean info) {
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
