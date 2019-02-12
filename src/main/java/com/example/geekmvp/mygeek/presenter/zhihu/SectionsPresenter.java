package com.example.geekmvp.mygeek.presenter.zhihu;

import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.base.IView;
import com.example.geekmvp.mygeek.model.bean.zhihu.SectionListBean;
import com.example.geekmvp.mygeek.model.http.zhihu.SectionsModel;
import com.example.geekmvp.mygeek.view.zhihu.ISectionsView;

public class SectionsPresenter<V extends ISectionsView> extends BasePresenter<V> implements SectionsModel.SectionsFinish {

    private SectionsModel sectionsModel = new SectionsModel();

    public void getSectionsData(){
        if(sectionsModel != null){
            sectionsModel.setSectionsContent(this);
        }
    }

    @Override
    public void setContent(SectionListBean info) {
        if(view != null){
            view.showContent(info);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
