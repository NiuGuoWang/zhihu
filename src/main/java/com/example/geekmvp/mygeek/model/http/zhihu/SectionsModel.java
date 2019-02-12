package com.example.geekmvp.mygeek.model.http.zhihu;

import com.example.geekmvp.mygeek.base.BaseObserver;
import com.example.geekmvp.mygeek.model.bean.zhihu.SectionListBean;
import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;
import com.example.geekmvp.mygeek.model.http.HttpManager;
import com.example.geekmvp.mygeek.utils.RxUtils;

public class SectionsModel {
    public interface SectionsFinish extends HttpFinishCallback{
        void setContent(SectionListBean info);
    }

    public void setSectionsContent(final SectionsFinish sectionsContent){
        HttpManager.getZhihuApis().getSectionList().compose(RxUtils.<SectionListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Object>(sectionsContent) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        sectionsContent.setContent((SectionListBean) o);
                    }
                });
    }
}
