package com.mediading.stealthystriver.network;

/**
 *  学习了晨曦大佬的写法
 *  <p>
 *      在实际的网络请求中会需要打印日志和一些请求时间的显示，方便排查问题
 */
public interface INetworkRequiredInfo {

    /**
     * 获取App版本名
     */
    String getAppVersionName();

    /**
     * 获取App版本号
     */
    String getAppVersionCode();

    /**
     * 判断是否为Debug模式
     */
    boolean isDebug();

}
