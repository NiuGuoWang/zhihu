package com.example.geekmvp.mygeek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        initStart();

    }

    /**
     * 启动页的作用
     * 1.作网络判断
     * 2.初始化本地数据到缓存（GreenDao）
     * 3.第一个界面的数据加载
     * 4.读取本地的一些配置文件
     */
    private void initStart() {

    }


    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        //跳转到主页
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
