package com.example.geekmvp.mygeek.base;

/**
 * View最顶部的接口
 */
public interface IView {

    //显示错误提示信息
    void showError(String msg);

    //显示loading
    void showLoading(boolean bool);

    //夜间模式切换
    void userNightModel(boolean night);

}
