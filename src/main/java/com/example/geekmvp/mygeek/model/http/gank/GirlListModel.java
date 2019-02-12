package com.example.geekmvp.mygeek.model.http.gank;

import com.example.geekmvp.mygeek.base.BaseObserver;
import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;
import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;
import com.example.geekmvp.mygeek.model.http.HttpManager;
import com.example.geekmvp.mygeek.utils.RxUtils;

public class GirlListModel {
    public interface GirlListFinish extends HttpFinishCallback {
        void setContent(GankItemBean info);
    }

    public void getRandomGirlData(final GirlListModel.GirlListFinish girlListFinish, int num ,int page){
        HttpManager.getGankApis().getGirlList(num, page)
                .compose(RxUtils.<GankItemBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Object>(girlListFinish) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        girlListFinish.setContent((GankItemBean) o);
                    }
                });
    }
}
