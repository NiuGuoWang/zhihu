package com.example.geekmvp.mygeek;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.geekmvp.mygeek.ui.gank.fragment.GankFragment;
import com.example.geekmvp.mygeek.ui.login.LoginActivity;
import com.example.geekmvp.mygeek.ui.userinfo.UserInfoActivity;
import com.example.geekmvp.mygeek.ui.wx.fragment.WXFragment;
import com.example.geekmvp.mygeek.ui.zhihu.fragment.ZhihuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.navigationView)
    NavigationView navigationView;

    //知乎fragment
    ZhihuFragment zhihuFragment;
    //微信fragment
    WXFragment wxFragment;
    //干货fragment
    GankFragment gankFragment;

    //侧滑
    private ActionBarDrawerToggle mDrawerToggle;
    private Context context;
    private ImageView img_head;
    private TextView txt_username;
    private boolean isLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        initView();
        login();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        isLogin = sp.getBoolean("isLogin", false);
        if(isLogin){
            String userimg = sp.getString("userImg", "userimg");
            RequestOptions options = RequestOptions.circleCropTransform().override(200,200);
            Glide.with(context).load(userimg).apply(options).into(img_head);
        }
    }

    private void login() {

        View headerView = navigationView.getHeaderView(0);
        img_head = headerView.findViewById(R.id.img_head);
        txt_username = headerView.findViewById(R.id.txt_username);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);

        String username = sp.getString("username", "未登录");
        String userimg = sp.getString("userImg", "userimg");
        txt_username.setText(username);

        if(userimg == "userimg"){

        }else{
            RequestOptions options = RequestOptions.circleCropTransform().override(200,200);
            Glide.with(context).load(userimg).apply(options).into(img_head);
        }

        img_head.setOnClickListener(this);
        txt_username.setOnClickListener(this);
    }

    private void initView(){

        //知乎初始化
        zhihuFragment = new ZhihuFragment();
        //微信初始化
        wxFragment = new WXFragment();
        //干货初始化
        gankFragment = new GankFragment();

        toolbar.setTitle("知乎日报");

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        navigationView.setNavigationItemSelectedListener(this);
        //初始化第一个界面
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_main_content,zhihuFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


    /**
     * 侧滑导航点击事件
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int _id = menuItem.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (_id){
            case R.id.nav_zhihu:
                transaction.replace(R.id.fl_main_content,zhihuFragment).commit();
                toolbar.setTitle("知乎日报");
                break;
            case R.id.nav_wx:
                transaction.replace(R.id.fl_main_content,wxFragment).commit();
                toolbar.setTitle("微信精选");
                break;
            case R.id.nav_gank:
                transaction.replace(R.id.fl_main_content,gankFragment).commit();
                toolbar.setTitle("干货集中营");
                break;
            case R.id.nav_collect:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_head:
                if(txt_username.getText().toString() != "未登录"){
                    Toast.makeText(context, "跳转用户信息界面！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, UserInfoActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(context, "跳转登陆界面！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.txt_username:
                if(txt_username.getText().toString() != "未登录"){
                    new AlertDialog.Builder(context)
                            .setTitle("是否退出登录")
                            .setNegativeButton("否", null)
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.clear();
                                    edit.commit();
                                    txt_username.setText("未登录");
                                    img_head.setImageResource(R.mipmap.ic_launcher_round);
                                }
                            })
                            .create()
                            .show();
                }else{
                    Toast.makeText(context, "跳转登陆界面！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
