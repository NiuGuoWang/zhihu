package com.example.geekmvp.mygeek.view.myserver;

import com.example.geekmvp.mygeek.base.IView;
import com.example.geekmvp.mygeek.model.bean.myserver.RegisterBean;

public interface IRegisterView extends IView {
    void showContent(RegisterBean info);
}
