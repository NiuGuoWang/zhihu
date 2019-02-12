package com.example.geekmvp.mygeek.view.zhihu;

import com.example.geekmvp.mygeek.base.IView;
import com.example.geekmvp.mygeek.model.bean.zhihu.SectionListBean;

public interface ISectionsView extends IView  {
    void showContent(SectionListBean info);
}
