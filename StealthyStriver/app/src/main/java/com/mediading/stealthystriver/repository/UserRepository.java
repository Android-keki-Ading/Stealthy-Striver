package com.mediading.stealthystriver.repository;

import android.os.AsyncTask;
import android.util.Log;

import com.mediading.stealthystriver.StealthyStriverApplication;
import com.mediading.stealthystriver.db.entity.User;
import com.mediading.stealthystriver.model.InfoResponse;
import com.mediading.stealthystriver.model.InfoUpdateResponse;
import com.mediading.stealthystriver.model.LoginResponse;
import com.mediading.stealthystriver.model.RegisterResponse;
import com.mediading.stealthystriver.model.UserInfo;
import com.mediading.stealthystriver.model.UserInfoResponse;
import com.mediading.stealthystriver.model.UserLogin;
import com.mediading.stealthystriver.model.UserRegister;
import com.mediading.stealthystriver.network.ServiceCreator;
import com.mediading.stealthystriver.network.api.ApiUserService;
import com.mediading.stealthystriver.utils.Constant;
import com.mediading.stealthystriver.utils.MVUtils;
import com.mediading.stealthystriver.utils.MVUtilsEntryPoint;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.EntryPointAccessors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mediading.stealthystriver.StealthyStriverApplication.getContext;

public class UserRepository {
    private final MVUtils mvUtils;
    private final String TAG = this.getClass().getName();
    private MutableLiveData<RegisterResponse> registerResponse = new MutableLiveData<>();
    private LiveData<User> localUser;

    private MutableLiveData<InfoResponse> infoResponse = new MutableLiveData<>();
    private final MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();
    private MutableLiveData<UserInfoResponse> userInfoResponse = new MutableLiveData<>();

    /**
     * 使用@Inject注入注解给一个类构造方法
     */
    @Inject
    public UserRepository() {
        Log.i(TAG, "UserRepo Created");
        MVUtilsEntryPoint entryPoint = EntryPointAccessors.fromApplication(getContext(), MVUtilsEntryPoint.class);
        mvUtils = entryPoint.getMVUtils();
    }

    public MutableLiveData<InfoResponse> getInfoResponse() {
        this.userInfoResponse = getUserInfoResponse(mvUtils.getString(Constant.LOGIN_TOKEN));
        // 此处有个bug，涉及网络操作得到userInfoResponse,有时延,不能在这里设置postValue(infoResponse)
        // 可以放在网络请求userInfoResponse结束之后, 直接取出data赋值给infoResponse
        if(this.userInfoResponse.getValue()!=null){
            infoResponse.postValue(this.userInfoResponse.getValue().getData());
        }
        else{
            infoResponse.postValue(new InfoResponse("","","","","",(byte) 0,""));
        }
        return infoResponse;
    }


    public MutableLiveData<UserInfoResponse> getUserInfoResponse(String token) {
        ServiceCreator
                .createService(ApiUserService.class)
                .getUserInfo(token)
                .enqueue(new Callback<UserInfoResponse>() {
                    @Override
                    public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                        if(response.code()==200){
                            Log.i(TAG,"getUserInfoResponse onResponse 200 "+response.body().toString());
                            userInfoResponse.postValue(response.body());

                            if(response.body().getData().getEmail()==null)
                                response.body().getData().setEmail("");
                            // 下面这行不能放在 getInfoResponse()
                            infoResponse.postValue(response.body().getData());
                        }
                    }
                    @Override
                    public void onFailure(Call<UserInfoResponse> call, Throwable t) {

                    }
                });
        return  userInfoResponse;
    }

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
                                    Log.i(TAG, "onResponse 请求成功,Msg:"+response.body().getMsg());
                                    if(response.body().getCode().equals("500")){
                                        response.body().setMsg(Constant.USER_EXIST);
                                    }
                                    registerResponse.postValue(response.body());
                                } else{
                                    if(response.code() == 400){  // 400 用户名或密码为空
                                        return;
                                    }
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
                        if (response.code() == 200 && response.body().getCode().equals("200")) {
                            Log.i(TAG, "onResponse 请求成功");
                            loginResponse.postValue(response.body());
                            mvUtils.put(Constant.LOGIN_TOKEN,response.body().getData());
                            Log.i(TAG,"user token saved --> "+response.body().getData());

                        } else  // 如果response响应非200,那么返回的body为空，即response.body()为空
                        {
                            if(response.code() == 400){  // 400 用户名或密码为空
                                return;
                            }
                            if(response.body()!=null && response.body().getCode().equals("500")){
                                loginResponse.postValue(new LoginResponse("","",Constant.USER_LOGIN_FAILED));
                            }
                            else
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
//        user.setId(1);
//        user.setName("Ading");
        user.setId(user.getId());
        AsyncTask.execute(new Runnable()
        {
            @Override
            public void run()
            {
                StealthyStriverApplication.getLocalDB().userDAO().insertUser(user);
                Log.i(TAG, "insertUser()->" + user.getName());
            }
        });
    }


    public void updateUserInfo() {
        ServiceCreator
                .createService(ApiUserService.class)
                .updateUserInfo(mvUtils.getString(Constant.LOGIN_TOKEN),this.infoResponse.getValue())
                .enqueue(new Callback<InfoUpdateResponse>() {
                    @Override
                    public void onResponse(Call<InfoUpdateResponse> call, Response<InfoUpdateResponse> response) {
                        Log.i(TAG,"updateUserInfo()--> onResponse 200 "+response.body());
                    }

                    @Override
                    public void onFailure(Call<InfoUpdateResponse> call, Throwable t) {

                    }
                });

    }
}
