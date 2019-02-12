package com.example.geekmvp.mygeek.model.http.zhihu;


import com.example.geekmvp.mygeek.base.BaseObserver;
import com.example.geekmvp.mygeek.model.bean.zhihu.DailyBeforeListBean;
import com.example.geekmvp.mygeek.model.bean.zhihu.DailyListBean;
import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;
import com.example.geekmvp.mygeek.model.http.HttpManager;
import com.example.geekmvp.mygeek.utils.RxUtils;

public class RibaoModel {

    public interface RibaoFinish extends HttpFinishCallback {
        void setContent(DailyListBean info);
        void setMoreContent(DailyBeforeListBean dailyBeforeListBean);
    }

    /**
     * 请求日报数据
     * @param ribaoFinish
     */
    public void getDailyData(final RibaoFinish ribaoFinish){
        ribaoFinish.showProgress();
        //封装后的请求数据加载方法
        HttpManager.getZhihuApis().getDailyList().compose(RxUtils.<DailyListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Object>(ribaoFinish) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        ribaoFinish.setContent((DailyListBean) o);
                    }
                });
    }

}
