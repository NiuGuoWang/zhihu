package com.example.geekmvp.mygeek.model.http.myserver;

import com.example.geekmvp.mygeek.base.BaseObserver;
import com.example.geekmvp.mygeek.model.bean.myserver.LoginBean;
import com.example.geekmvp.mygeek.model.bean.myserver.VerifyBean;
import com.example.geekmvp.mygeek.model.bean.zhihu.DailyListBean;
import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;
import com.example.geekmvp.mygeek.model.http.HttpManager;
import com.example.geekmvp.mygeek.utils.RxUtils;

public class VerifyModel {

    public interface VerifyFinish extends HttpFinishCallback{
        void setContent(VerifyBean info);
    }

    public void getVerifyData(final VerifyFinish verifyFinish) {
        verifyFinish.showProgress();
        HttpManager.getIMyserver().getVerify().compose(RxUtils.<VerifyBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Object>(verifyFinish) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        verifyFinish.setContent((VerifyBean)o);
                    }
                });
    }
}
