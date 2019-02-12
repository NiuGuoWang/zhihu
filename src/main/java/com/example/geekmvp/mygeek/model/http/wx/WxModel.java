package com.example.geekmvp.mygeek.model.http.wx;

import com.example.geekmvp.mygeek.base.BaseObserver;
import com.example.geekmvp.mygeek.model.bean.wx.WXItemBean;
import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;
import com.example.geekmvp.mygeek.model.http.HttpManager;
import com.example.geekmvp.mygeek.utils.RxUtils;

public class WxModel {
    public interface Wxfinish extends HttpFinishCallback{
        void showContent(WXItemBean info);
    }

    public void getWxData(final Wxfinish wxfinish, int page){
        HttpManager.getWxApis().getWXHot("52b7ec3471ac3bec6846577e79f20e4c", 20, page)
                .compose(RxUtils.<WXItemBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Object>(wxfinish) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        wxfinish.showContent((WXItemBean) o);
                    }
                });
    }
}
