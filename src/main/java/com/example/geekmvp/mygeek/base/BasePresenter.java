package com.example.geekmvp.mygeek.base;

import java.lang.ref.WeakReference;

/**
 * mvp中最顶部的P层
 * @param <T>
 */
public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    //用泛型接收View
    protected T view;
    //弱引用View
    private WeakReference<T> weakReference;

    //关联View到Persenter
    @Override
    public void attachView(T view) {
        weakReference = new WeakReference<T>(view);
        this.view = weakReference.get();
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
