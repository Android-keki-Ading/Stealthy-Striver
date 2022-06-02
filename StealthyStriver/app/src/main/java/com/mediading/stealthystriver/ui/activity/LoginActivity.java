package com.mediading.stealthystriver.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.mediading.stealthystriver.R;
import com.mediading.stealthystriver.databinding.ActivityLoginBinding;
import com.mediading.stealthystriver.model.LoginResponse;
import com.mediading.stealthystriver.viewmodel.LoginViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * 要加上下面这个注解，然后
 */
@AndroidEntryPoint
public class LoginActivity extends BaseActivity {
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.loginWithLocalUser();
        dataBinding.setLogin(loginViewModel);
        initView();
        Log.i(TAG,"onCreated");
    }


    private void initView() {
        loginViewModel.getDataStatus().observe(this,  loginStatus->{
            toastShort(loginStatus.getMsg());
        });


        dataBinding.btnLogin.setOnClickListener(e->{
            loginViewModel.checkUserLogin();
            Log.i(TAG,loginViewModel.getUserLogin().getValue().getLoginName());
            Log.i(TAG,loginViewModel.getDataStatus().getValue().getMsg());

            /**
             * 点击登录后才会触发 网络层访问后台登录api
             * 然后才观察登陆返回数据的liveData
             */

        });

        loginViewModel.getLoginResponse().observe(this, loginResponse -> {
            toastShort(loginResponse.getMsg());
        });


        dataBinding.tvRegister.setOnClickListener(e->{
            jump2Activity(RegisterActivity.class);
        });


    }
}