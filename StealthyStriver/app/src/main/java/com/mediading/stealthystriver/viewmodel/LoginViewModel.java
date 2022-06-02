package com.mediading.stealthystriver.viewmodel;

import android.util.Log;

import com.google.gson.Gson;
import com.mediading.stealthystriver.db.entity.User;
import com.mediading.stealthystriver.model.LoginResponse;
import com.mediading.stealthystriver.model.UserLogin;
import com.mediading.stealthystriver.repository.UserRepository;
import com.mediading.stealthystriver.utils.CheckUtils;
import com.mediading.stealthystriver.utils.Constant;

import javax.inject.Inject;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends BaseViewModel {

    private MutableLiveData<UserLogin> userLogin = new MutableLiveData<>();
    private final UserRepository userRepository;
    private MutableLiveData<LoginResponse> loginResponse;

    @Inject
    LoginViewModel(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
        userLogin.setValue(new UserLogin("Ading",""));
    }

    public MutableLiveData<UserLogin> getUserLogin() {
        return userLogin;
    }

    //db.entity.User
    private LiveData<User> localUser = Transformations.switchMap(userLogin, new Function<UserLogin, LiveData<User>>() {
        @Override
        public LiveData<User> apply(UserLogin input) {
            Log.i(TAG, "get localUser-->" + new Gson().toJson(input));
            return userRepository.getLocalUserByName(input.getLoginName());
        }
    });

    public MutableLiveData<DataStatus> getDataStatus() {
        return dataStatus;
    }
    public LiveData<User> getLocalUser() {
        return localUser;
    }

    public MutableLiveData<LoginResponse> getLoginResponse() {
        if(loginResponse==null){
            login();
        }
        return loginResponse;
    }

    public void checkUserLogin() {
        String loginName = userLogin.getValue().getLoginName();
        String passwd = userLogin.getValue().getPassword();
        if(CheckUtils.isNullString(loginName)){
            updateRegisterStatusMsg(Constant.LOGIN_NAME_NULL);
            return;
        }
        if(CheckUtils.isNullString(passwd)){
            updateRegisterStatusMsg(Constant.PASSWORD_NULL_MSG);
            return;
        }
        updateRegisterStatusMsg(Constant.LOGINING);

        login();
    }

    public void loginWithLocalUser() {
        Log.i(TAG,"loginWithLocalUser--> "+getUserLogin().getValue().getLoginName());
        userLogin.postValue(getUserLogin().getValue());
    }

    private void login(){
        Log.i(TAG,"logining-->");
        loginResponse = userRepository.getLoginResponse(userLogin.getValue());
    }

    @Override
    protected void initDataStatus() {
        dataStatus.postValue(new DataStatus("请先登录",""));
    }
}
