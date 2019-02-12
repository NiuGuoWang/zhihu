package com.example.geekmvp.mygeek.model.http.myserver;

import com.example.geekmvp.mygeek.base.BaseObserver;
import com.example.geekmvp.mygeek.model.bean.myserver.LoginBean;
import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;
import com.example.geekmvp.mygeek.model.http.HttpManager;
import com.example.geekmvp.mygeek.utils.RxUtils;

import java.util.Map;

public class LoginModel {
    public interface LoginFinish extends HttpFinishCallback{
        void setContent(LoginBean info);
    }

    public void getLoginData(final LoginFinish loginFinish, Map<String, Object> map){
        HttpManager.getIMyserver().getLogin(map).compose(RxUtils.<LoginBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Object>(loginFinish) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        loginFinish.setContent((LoginBean) o);
                    }
                });
    }
}
