package com.example.geekmvp.mygeek.model.http.myserver;

import com.example.geekmvp.mygeek.base.BaseObserver;
import com.example.geekmvp.mygeek.model.bean.myserver.RegisterBean;
import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;
import com.example.geekmvp.mygeek.model.http.HttpManager;
import com.example.geekmvp.mygeek.utils.RxUtils;

import java.util.Map;

public class RegisterModel {
    public interface RegisterFinish extends HttpFinishCallback{
        void setContent(RegisterBean info );
    }
    public void getRegisterData(final RegisterFinish registerFinish, Map<String, Object> map ){
        registerFinish.showProgress();
        HttpManager.getIMyserver().getRegister(map).compose(RxUtils.<RegisterBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Object>(registerFinish) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        registerFinish.setContent((RegisterBean) o);
                    }
                });
    }
}
