package com.example.geekmvp.mygeek.base;


import com.example.geekmvp.mygeek.model.http.HttpFinishCallback;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {

    private HttpFinishCallback httpFinishCallback;

    public BaseObserver(HttpFinishCallback httpFinishCallback){
        this.httpFinishCallback = httpFinishCallback;
    }

    //解决Rxjava内存泄漏
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onNext(Object o) {

    }

    @Override
    public void onError(Throwable e) {
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
        if(httpFinishCallback != null){
            httpFinishCallback.hideProgress();
        }
    }

    @Override
    public void onComplete() {
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
        if(httpFinishCallback != null){
            httpFinishCallback.hideProgress();
        }
    }
}
