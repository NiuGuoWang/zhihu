package com.example.geekmvp.mygeek.model.http.gank;

import com.example.geekmvp.mygeek.base.BaseObserver;
import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;
import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;
import com.example.geekmvp.mygeek.model.http.HttpManager;
import com.example.geekmvp.mygeek.utils.RxUtils;

public class GankModel {
    public interface GankFinish extends HttpFinishCallback{
        void setContent(GankItemBean info);
    }

    public void getGankData(final GankFinish gankFinish, String tech, int num, int page){
        HttpManager.getGankApis().getTechList(tech, num, page)
                .compose(RxUtils.<GankItemBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Object>(gankFinish) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        gankFinish.setContent((GankItemBean) o);
                    }
                });
    }
}
