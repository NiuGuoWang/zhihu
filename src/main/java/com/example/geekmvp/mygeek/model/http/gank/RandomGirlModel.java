package com.example.geekmvp.mygeek.model.http.gank;

import com.example.geekmvp.mygeek.base.BaseObserver;
import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;
import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;
import com.example.geekmvp.mygeek.model.http.HttpManager;
import com.example.geekmvp.mygeek.utils.RxUtils;

public class RandomGirlModel {
    public interface RandomGirlFinish extends HttpFinishCallback{
        void setContent(GankItemBean info);
    }

    public void getRandomGirlData(final RandomGirlFinish randomGirlFinish, int num){
        HttpManager.getGankApis().getRandomGirl(num)
                .compose(RxUtils.<GankItemBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Object>(randomGirlFinish) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        randomGirlFinish.setContent((GankItemBean) o);
                    }
                });
    }
}
