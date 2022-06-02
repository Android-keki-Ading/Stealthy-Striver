package com.mediading.stealthystriver.repository;

import android.os.AsyncTask;
import android.util.Log;

import com.mediading.stealthystriver.StealthyStriverApplication;
import com.mediading.stealthystriver.db.entity.User;
import com.mediading.stealthystriver.model.LoginResponse;
import com.mediading.stealthystriver.model.RegisterResponse;
import com.mediading.stealthystriver.model.UserLogin;
import com.mediading.stealthystriver.model.UserRegister;
import com.mediading.stealthystriver.network.ServiceCreator;
import com.mediading.stealthystriver.network.api.ApiUserService;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private final String TAG = this.getClass().getName();
    private MutableLiveData<RegisterResponse> registerResponse = new MutableLiveData<>();
    private LiveData<User> localUser;
    private final MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();

    public MutableLiveData<RegisterResponse> getRegisterResponse(UserRegister userRegister) {

        Log.i(TAG, "getRegisterResponse --> called");
        ServiceCreator
                .createService(ApiUserService.class)
                .register(userRegister)
                .enqueue(
                        new Callback<RegisterResponse>() {
                            @Override
                            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                                if (response.code() == 200) {
                                    Log.i(TAG, "onResponse 请求成功");
                                    registerResponse.postValue(response.body());
                                } else{
                                    registerResponse.postValue(new RegisterResponse("","","服务器好像开小差了~请稍后再试"));
                                    Log.i(TAG, "onResponse response is null: " + response);
                                }
                            }
                            @Override
                            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                                Log.i(TAG, "onFailure" + t.getMessage());

                            }
                        });
        return registerResponse;
    }

    /**
     * 使用@Inject注入注解给一个类构造方法
     */
    @Inject
    public UserRepository() {
        Log.i(TAG, "UserRepo Created");
    }

    public LiveData<User> getLocalUserByName(String name){
        localUser = StealthyStriverApplication.getLocalDB().userDAO().getUserByName(name);
        return localUser;
    }

    public MutableLiveData<LoginResponse> getLoginResponse(UserLogin userLogin){
        Log.i(TAG, "getLoginResponse --> called");
        ServiceCreator
                .createService(ApiUserService.class)
                .login(userLogin)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.code() == 200) {
                            Log.i(TAG, "onResponse 请求成功");
                            loginResponse.postValue(response.body());
                        } else  // 如果response响应非200,那么返回的body为空，即response.body()为空
                        {
                            loginResponse.postValue(new LoginResponse("","","服务器好像开小差了~请稍后再试"));
                            Log.i(TAG, "onResponse response is null: " + response);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        loginResponse.postValue(new LoginResponse("","","服务器好像开小差了~请稍后再试"));
                        Log.i(TAG, "onFailure" + t.getMessage());
                    }
                });
        return loginResponse;
    }

    public void saveUser(User user){
        user.setId(1);
        user.setName("Ading");
        AsyncTask.execute(new Runnable()
        {
            @Override
            public void run()
            {
                StealthyStriverApplication.getLocalDB().userDAO().insertUser(user);
                Log.i(TAG, "insertUser()->" + user);
            }
        });
    }
}
