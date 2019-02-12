package com.example.geekmvp.mygeek.model.http.myserver;

import com.example.geekmvp.mygeek.base.BaseObserver;
import com.example.geekmvp.mygeek.model.bean.myserver.UploadHeaderBean;
import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;
import com.example.geekmvp.mygeek.model.http.HttpManager;
import com.example.geekmvp.mygeek.utils.RxUtils;

import java.util.Map;

public class UploadheaderModel {
    public interface UploadheaderFinish extends HttpFinishCallback{
        void setContent(UploadHeaderBean info);
    }

    public void getUploadheaderData(final UploadheaderFinish uploadheaderFinish, Map<String, Object> map){
        uploadheaderFinish.showProgress();
        HttpManager.getIMyserver().getUploadheader(map)
                .compose(RxUtils.<UploadHeaderBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Object>(uploadheaderFinish) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        uploadheaderFinish.setContent((UploadHeaderBean) o);
                    }
                });
    }
}
