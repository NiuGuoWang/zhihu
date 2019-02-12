package com.example.geekmvp.mygeek.presenter.myserver;

import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.model.bean.myserver.UploadHeaderBean;
import com.example.geekmvp.mygeek.model.http.myserver.UploadheaderModel;
import com.example.geekmvp.mygeek.view.myserver.IUploadheaderView;

import java.util.Map;

public class UploadheaderPresenter<V extends IUploadheaderView> extends BasePresenter<V> implements UploadheaderModel.UploadheaderFinish{
    private UploadheaderModel uploadheaderModel = new UploadheaderModel();

    public void getUploadHeaderData(Map<String, Object> map){
        if(uploadheaderModel != null){
            uploadheaderModel.getUploadheaderData(this, map);
        }
    }

    @Override
    public void setContent(UploadHeaderBean info) {
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
