package com.example.geekmvp.mygeek.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geekmvp.mygeek.MainActivity;
import com.example.geekmvp.mygeek.R;
import com.example.geekmvp.mygeek.base.BasePresenter;
import com.example.geekmvp.mygeek.base.IView;
import com.example.geekmvp.mygeek.model.bean.myserver.LoginBean;
import com.example.geekmvp.mygeek.presenter.myserver.LoginPresenter;
import com.example.geekmvp.mygeek.ui.register.RegisterActivity;
import com.example.geekmvp.mygeek.view.myserver.ILoginView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity<V extends IView, T extends BasePresenter<V>> extends AppCompatActivity implements ILoginView{

    @BindView(R.id.et_name_login)
    EditText etNameLogin;
    @BindView(R.id.et_paw_login)
    EditText etPawLogin;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    public static LoginActivity instance;
    protected T presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        instance = this;
    }

    @OnClick({R.id.bt_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                String name = etNameLogin.getText().toString();
                String paw = etPawLogin.getText().toString();
                presenter = (T) new LoginPresenter<>();
                if(presenter !=null)presenter.attachView((V) this);
                Map<String, Object> map = new HashMap<>();
                map.put("username", name);
                map.put("password", paw);
                ((LoginPresenter)presenter).getLoginData(map);
                break;
            case R.id.tv_register:
                Toast.makeText(instance, "跳转注册页面！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(instance, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showContent(LoginBean loginBean) {
        Toast.makeText(instance, loginBean.getRet(), Toast.LENGTH_SHORT).show();
        if(loginBean.getCode() == 200){
            SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("username", etNameLogin.getText().toString());
            edit.putString("password", etPawLogin.getText().toString());
            edit.putString("uid", loginBean.getData().get(0).getUid());
            edit.putString("userImg", (String) loginBean.getData().get(0).getHeader());
            edit.putBoolean("isLogin", true);
            edit.commit();
            Intent intent = new Intent(instance, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showLoading(boolean bool) {

    }

    @Override
    public void userNightModel(boolean night) {

    }
}
