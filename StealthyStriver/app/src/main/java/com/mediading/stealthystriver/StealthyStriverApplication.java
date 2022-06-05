package com.mediading.stealthystriver;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.mediading.stealthystriver.db.LocalDataBase;
import com.mediading.stealthystriver.network.ServiceCreator;
import com.mediading.stealthystriver.ui.activity.ActivityManager;

import dagger.hilt.android.HiltAndroidApp;
import dagger.hilt.android.internal.managers.ApplicationComponentManager;

/**
 * 所有使用Hilt框架的应用都必须先使用@HiltAndroidApp来注解应用的Application
 */

@HiltAndroidApp
public class StealthyStriverApplication extends Application {
    public static final String TAG = StealthyStriverApplication.class.getSimpleName();

//    public  static final String  BASE_URL = "http://106.55.171.55:8088/";
    public  static final String  BASE_URL = "http://192.168.249.91:8088/";

    public static LocalDataBase db;

    /** 这里获取的不是Activity或Service中的Context，而是Application的，
     * 它全局只会存在 一份实例, 且在整个应用程序的生命周期内都不会回收
     * 因此不存在内存泄漏风险， 可以用  @SuppressLint("StaticFieldLeak")忽略警告
     * @date 2022-05-24 10:41
     */
    @SuppressLint("StaticFieldLeak")
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        //初始化网络框架
        ServiceCreator.init(new NetworkRequiredInfo(this));

        //创建本地数据库
        db = LocalDataBase.getDBInstance(context);

    }

    public static Context getContext() {
        return context;
    }

    public static LocalDataBase getLocalDB(){
        return db;
    }

    public static ActivityManager getActivityManager() {
        return ActivityManager.getInstance();
    }
}
