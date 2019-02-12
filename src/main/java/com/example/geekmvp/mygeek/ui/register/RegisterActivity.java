package com.example.geekmvp.mygeek.ui.register;

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
import com.example.geekmvp.mygeek.model.bean.myserver.RegisterBean;
import com.example.geekmvp.mygeek.model.bean.myserver.VerifyBean;
import com.example.geekmvp.mygeek.presenter.myserver.RegisterPresenter;
import com.example.geekmvp.mygeek.presenter.myserver.VerifyPresenter;
import com.example.geekmvp.mygeek.ui.login.LoginActivity;
import com.example.geekmvp.mygeek.view.myserver.IRegisterView;
import com.example.geekmvp.mygeek.view.myserver.IVerifyView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity<V extends IView, T extends BasePresenter<V>> extends AppCompatActivity implements IVerifyView, IRegisterView {

    @BindView(R.id.et_name_register)
    EditText etNameRegister;
    @BindView(R.id.et_paw_register)
    EditText etPawRegister;
    @BindView(R.id.et_phone_register)
    EditText etPhoneRegister;
    @BindView(R.id.et_verify_register)
    EditText etVerifyRegister;
    @BindView(R.id.tv_verify_register)
    TextView tvVerifyRegister;
    @BindView(R.id.bt_register)
    Button btRegister;
    private Context context;
    protected T presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        context = this;

    }

    @OnClick({R.id.tv_verify_register, R.id.bt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_verify_register:
                presenter = (T) new VerifyPresenter<>();
                if(presenter != null) presenter.attachView((V) this);
                ((VerifyPresenter)presenter).getVerifyData();
                break;
            case R.id.bt_register:
                presenter = (T) new RegisterPresenter<>();
                if(presenter != null) presenter.attachView((V) this);
                Map<String, Object> map = new HashMap<>();
                map.put("username",etNameRegister.getText().toString());
                map.put("password",etPawRegister.getText().toString());
                map.put("phone", etPhoneRegister.getText().toString());
                map.put("verify",etVerifyRegister.getText().toString());
                ((RegisterPresenter)presenter).getRegisterData(map);
                break;
        }
    }

    @Override
    public void showContent(VerifyBean info) {
        String data = info.getData();
        tvVerifyRegister.setText(data);
    }

    @Override
    public void showContent(RegisterBean info) {
        Toast.makeText(context, info.getRet(), Toast.LENGTH_SHORT).show();
        if(info.getCode()==200){
            finish();
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
