package com.mediading.stealthystriver.utils;

public class CheckUtils {
    public static boolean isNullString(String data){
        return "".equals(data) || data == null;
    }

    /**
     * Compares this string to the specified object.
     * The result is true if and only if the argument is not null and is a String object that represents the same sequence of characters as this object.
     * @param data1
     * @param data2
     * @return
     */
    public static boolean isNotTheSameString(String data1, String data2){
        return !data1.equals(data2);
    }

    public static boolean isNullObj(Object obj){
        return obj == null;
    }


    public static boolean isLogined(String msg) {
        return msg.equals("已登录");
    }
}
