package com.ading_keki.stealthy_striver.util;

import java.util.regex.Pattern;

/**
 * @project: stealthy_striver
 * @Created-Time: 2022-04-07 18:07
 * @Author: Ading
 * @file_desc:
 */
public class CheckUtil {
    public static boolean isPhone(String str){
        /** 判断是否是手机号
         * @param: [number]
         * @return boolean
         */
        return Pattern.matches("^(?:(?:\\+|00)86)?1[3-9]\\d{9}$", str);
    }

    public static boolean isEmail(String str){
        /** 判断是否是邮箱
         * @param: [email]
         * @return boolean
         */
        return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", str);
    }

    public static boolean isLogName(String str){
        return (isPhone(str) || isEmail(str));
    }

    public static boolean checkMD5(String md5){
        /** 判断是否是md5
         * @param: [md5]
         * @return boolean
         */
        return Pattern.matches("^([a-f\\d]{32}|[A-F\\d]{32})$", md5);
    }

}
