package com.example.geekmvp.mygeek.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<V extends IView,P extends BasePresenter<V>> extends AppCompatActivity{

    //对应的上文
    protected Context context;

    //基类p层
    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        context = this;
        onViewCreated();
        initView();
    }

    //自定义的界面创建完成的方法
    protected void onViewCreated(){
        mPresenter = createPresenter();
        if(mPresenter != null){
            mPresenter.attachView((V)this);
        }
    }

    //获取R文件里面的布局的ID
    protected abstract int getLayout();
    //有子类重写创建对应的p层
    protected abstract P createPresenter();
    //初始化数据
    protected abstract void initView();

    @Override
    protected void onResume() {
        super.onResume();
        if(mPresenter != null){
            mPresenter.attachView((V) this);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }

    }
}
