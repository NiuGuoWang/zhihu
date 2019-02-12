package com.example.geekmvp.mygeek.model.http.zhihu;

import com.example.geekmvp.mygeek.base.BaseObserver;
import com.example.geekmvp.mygeek.model.bean.zhihu.HotListBean;
import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;
import com.example.geekmvp.mygeek.model.http.HttpManager;
import com.example.geekmvp.mygeek.utils.RxUtils;

public class HotModel{
    public interface HotFinish extends HttpFinishCallback{
        void setContent(HotListBean info);
    }

    public void getHotData(final HotFinish hotFinish){
        HttpManager.getZhihuApis().getHotList().compose(RxUtils.<HotListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Object>(hotFinish) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        hotFinish.setContent((HotListBean) o);
                    }
                });
    }
}
