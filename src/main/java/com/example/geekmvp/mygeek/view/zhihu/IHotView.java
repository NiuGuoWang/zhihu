package com.example.geekmvp.mygeek.view.zhihu;

import com.example.geekmvp.mygeek.base.IView;
import com.example.geekmvp.mygeek.model.bean.zhihu.HotListBean;

public interface IHotView extends IView {
    void showContent(HotListBean info);
}
