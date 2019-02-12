package com.example.geekmvp.mygeek.base;

//P层的接口基类（最顶端）
public interface IPresenter<T extends IView> {

    //关联view
    void attachView(T view);

    //取消关联
    void detachView();

}
