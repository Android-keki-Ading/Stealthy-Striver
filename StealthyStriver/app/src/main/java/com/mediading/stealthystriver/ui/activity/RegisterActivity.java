package com.mediading.stealthystriver.ui.activity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

import android.os.Bundle;
import android.util.Log;

import com.mediading.stealthystriver.databinding.ActivityRegisterBinding;
import com.mediading.stealthystriver.R;
import com.mediading.stealthystriver.db.entity.User;
import com.mediading.stealthystriver.model.RegisterResponse;
import com.mediading.stealthystriver.model.UserRegister;
import com.mediading.stealthystriver.view.LoadingDialog;
import com.mediading.stealthystriver.viewmodel.RegisterViewModel;

import java.util.Objects;

@AndroidEntryPoint
public class RegisterActivity extends BaseActivity {
    private ActivityRegisterBinding dataBinding;
    private RegisterViewModel registerViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
//        registerViewModel.register();
        dataBinding.setRegister(registerViewModel);
        initView();
        Log.i(TAG,"onCreated");
    }

    private void initView(){

        registerViewModel.getDataStatus().observe(this, registerStatus->{
            dissmissLoading();
            toastShort(registerStatus.getMsg());
        });

        // 这里设置观察RegisterResponse, toast 后台返回的msg
        registerViewModel.getRegisterResponse().observe(this, new Observer<RegisterResponse>() {
            @Override
            public void onChanged(RegisterResponse registerResponse) {
                Log.i(TAG,"getRegisterResponse().observe"+registerResponse.getMsg());
                toastShort(registerResponse.getMsg());
                registerViewModel.saveUser();
                if(registerResponse.getMsg().equals("注册成功！")){
                    Log.i(TAG,""+registerResponse.getMsg()+ "跳转登录...");
                    jump2ActivityFinish(LoginActivity.class);
                }
            }
        });

        dataBinding.btnRegister.setOnClickListener(e->{
            showLoading();
            // 点击注册, 将视图绑定到userRegister的对象，放入(注册）MutableLiveData<UserRegister>
            // 由此触发Transformations.switchMap()返回LiveData<RegisterResponse>

            //检查用户输入再登录 ,, 这里传入的是用户输入的, 确认密码 ,在ViewModle.UserRegister中再多一个字段了,
            registerViewModel.checkUserRegister(dataBinding.getConfirmPasswd());
//
//            registerViewModel.getDataStatus().observe(this, registerStatus->{
//                toastShort(registerStatus.getMsg());
//            });
//            Log.i(TAG,registerViewModel.getUserRegister().getValue().getEMail());
//            Log.i(TAG,registerViewModel.getDataStatus().getValue().getMsg());
        });

    }
}