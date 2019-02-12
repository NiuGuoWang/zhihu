package com.example.geekmvp.mygeek.view.zhihu;


import com.example.geekmvp.mygeek.base.IView;
import com.example.geekmvp.mygeek.model.bean.zhihu.DailyBeforeListBean;
import com.example.geekmvp.mygeek.model.bean.zhihu.DailyListBean;

/**
 * 日报View接口
 */
public interface IRibaoView extends IView {

    //显示信息
    void showContent(DailyListBean info);

    void showMoreContent(DailyBeforeListBean dailyBeforeListBean);
}
