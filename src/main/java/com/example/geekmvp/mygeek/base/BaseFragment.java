package com.example.geekmvp.mygeek.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends IView,T extends BasePresenter<V>> extends Fragment implements IView {

    //P层
    protected T presenter;
    //Butterknife
    protected Unbinder unbinder;
    //抽取Fragment对应的Activity和Context
    protected Activity activity;
    protected Context mContext;

    //Fragment生命周期方法
    @Override
    public void onAttach(Context context) {
        activity = (Activity)context;
        mContext = context;
        super.onAttach(context);
    }

    //创建界面
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(),null);
        return view;
    }

    //创建完界面绑定p层2
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //获取Fragment子类创建的Presenter
        presenter = createPresenter();
        //关联View
        if(presenter != null) presenter.attachView((V) this);
        unbinder = ButterKnife.bind(this,view);
        initView(view);
        initData();
    }

    //界面释放
    @Override
    public void onDestroyView() {
        if(presenter != null) presenter.detachView();
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean bool) {

    }

    @Override
    public void userNightModel(boolean night) {

    }

    //获取当前界面布局的R文件的ID
    protected abstract int getLayout();

    //创建p层
    protected abstract T createPresenter();

    //初始化界面
    protected abstract void initView(View mView);

    //初始化数据
    protected abstract void initData();
}
