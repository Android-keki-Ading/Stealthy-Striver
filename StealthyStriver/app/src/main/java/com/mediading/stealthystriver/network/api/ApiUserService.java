package com.mediading.stealthystriver.network.api;

import com.mediading.stealthystriver.model.InfoResponse;
import com.mediading.stealthystriver.model.LoginResponse;
import com.mediading.stealthystriver.model.RegisterResponse;
import com.mediading.stealthystriver.model.UserLogin;
import com.mediading.stealthystriver.model.UserRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 *  所有网络请求api
 * @author Ading
 */
public interface ApiUserService {

    @POST("api/v1/user/register")
    public Call<RegisterResponse> register(@Body UserRegister user);

    @POST("api/v1/user/login")
    public Call<LoginResponse> login(@Body UserLogin user);

    @GET("api/v2/user/info")
    public Call<InfoResponse> getUserInfo(@Body UserLogin user);

}
