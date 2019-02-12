package com.example.geekmvp.mygeek.view.myserver;

import com.example.geekmvp.mygeek.base.IView;
import com.example.geekmvp.mygeek.model.bean.myserver.LoginBean;

public interface ILoginView extends IView {
    void showContent(LoginBean loginBean);
}
