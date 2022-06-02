package com.mediading.stealthystriver.network;

import android.util.Log;

import com.mediading.stealthystriver.StealthyStriverApplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class ServiceCreator {
    private static final String TAG = ServiceCreator.class.getSimpleName();

    /**
     * 获取APP运行状态及版本信息，用于日志打印
     */
    private static INetworkRequiredInfo iNetworkRequiredInfo;

    /** 服务器地址
     * @date 2022-05-24 10:50
     */
    private static final String BASE_URL= StealthyStriverApplication.BASE_URL;

    /** 创建静态retrofit 成员
     * @date 2022-05-24 10:50
     */
    private static final Retrofit retrofit =
            new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /**
     * 泛型方法,使用public修饰符，暴露创建接口
     * @param serviceClass  服务接口
     * @param <T> 返回泛型
     * @return apiService
     */
    public static <T> T createService(Class<T> serviceClass){
        Log.i(TAG,"createService --> "+serviceClass.getSimpleName());
        return retrofit.create(serviceClass);
    }

    public static void init(INetworkRequiredInfo networkRequiredInfo){
        iNetworkRequiredInfo = networkRequiredInfo;
    }
}
