package com.mediading.stealthystriver.viewmodel;

import android.util.Log;

import com.google.gson.Gson;
import com.mediading.stealthystriver.db.entity.User;
import com.mediading.stealthystriver.model.RegisterResponse;
import com.mediading.stealthystriver.model.UserRegister;
import com.mediading.stealthystriver.repository.UserRepository;
import com.mediading.stealthystriver.utils.CheckUtils;
import com.mediading.stealthystriver.utils.Constant;

import javax.inject.Inject;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RegisterViewModel extends BaseViewModel {
    private MutableLiveData<UserRegister> userRegister = new MutableLiveData<>();

    private final UserRepository userRepository;
    private final LiveData<RegisterResponse> registerResponse = Transformations.switchMap(userRegister, new Function<UserRegister, LiveData<RegisterResponse>>() {
        @Override
        public LiveData<RegisterResponse> apply(UserRegister input) {
            Log.d(TAG, "register: "+new Gson().toJson(input));
            return userRepository.getRegisterResponse(input);
        }
    });

    public LiveData<RegisterResponse> getRegisterResponse() {
        return registerResponse;
    }

    public MutableLiveData<UserRegister> getUserRegister() {
        return userRegister;
    }

    public void register(){
        userRegister
                .postValue(getUserRegister().getValue());
    }



    public void checkUserRegister(String confirmPasswd){
        String email = userRegister.getValue().getEMail();
        String passwd = userRegister.getValue().getPassword();
        if(CheckUtils.isNullString(email)){
            updateRegisterStatusMsg(Constant.EMAIL_NULL_MSG);
            return;
        }
        if(CheckUtils.isNullString(passwd)){
            updateRegisterStatusMsg(Constant.PASSWORD_NULL_MSG);
            return;
        }
        if(CheckUtils.isNullString(confirmPasswd)){
            updateRegisterStatusMsg(Constant.CONFIRM_PASSWORD_NULL_MSG);
            return;
        }
        if(CheckUtils.isNotTheSameString(confirmPasswd, passwd)){
            updateRegisterStatusMsg(Constant.CONFIRM_PASSWORD_NOT_SAME_MSG);
            return;
        }
        // 全部无误, 向后台注册
        updateRegisterStatusMsg(Constant.REGISTERING);

        register();


    }

    public void saveUser(){
        if("success".equals(registerResponse.getValue().getMsg())){
            String email = userRegister.getValue().getEMail();
            String passwd = userRegister.getValue().getPassword();
            User user = new User(email,passwd);
            userRepository.saveUser(user);
            Log.i(TAG,"saveUser");
        }
        else
            updateRegisterStatusMsg(Constant.SAVE_USER_FAILED);
    }

    /** 构造RegisterViewModel
     * <p>
     * 1. userRegister 初始化
     * <p>
     *     2. 设置数据状态初始值
     * <p>
     * 3. 注入UserRepo依赖
     */
    @Inject
    RegisterViewModel(UserRepository userRepository){
        super();
        this.userRegister.setValue(new UserRegister("",""));
        this.userRepository = userRepository;
    }

    @Override
    protected void initDataStatus() {
        super.getDataStatus().postValue(new DataStatus("欢迎注册偷博仔~",""));
    }
}
