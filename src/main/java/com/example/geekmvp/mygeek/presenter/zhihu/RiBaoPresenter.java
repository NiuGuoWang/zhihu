package com.example.geekmvp.mygeek.presenter.zhihu;


import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.zhihu.DailyBeforeListBean;
import com.example.geekmvp.mygeek.model.bean.zhihu.DailyListBean;
import com.example.geekmvp.mygeek.model.http.zhihu.RibaoModel;
import com.example.geekmvp.mygeek.view.zhihu.IRibaoView;

/**
 * P层对应对应的V层
 * @param <V>
 */
public class RiBaoPresenter<V extends IRibaoView> extends BasePresenter<V> implements RibaoModel.RibaoFinish {

    //日报接口的model类（加载数据封装数据）
    private RibaoModel ribaoModel = new RibaoModel();

    //给V层调用加载数据
    public void getDailyData(){
        if(ribaoModel != null){
            ribaoModel.getDailyData(this);
        }
    }

    //数据加载成功后的回调
    @Override
    public void setContent(DailyListBean info) {
        if(view != null){
            view.showContent(info);
        }
    }

    //数据加载成功后的回调
    @Override
    public void setMoreContent(DailyBeforeListBean dailyBeforeListBean) {
        if(view != null){
            view.showMoreContent(dailyBeforeListBean);
        }
    }

    //数据加载设置loading
    @Override
    public void showProgress() {
        if(view != null){

        }
    }

    //隐藏loading
    @Override
    public void hideProgress() {

    }
}
