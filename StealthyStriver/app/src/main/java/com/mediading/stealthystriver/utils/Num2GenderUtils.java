package com.mediading.stealthystriver.utils;

public class Num2GenderUtils {

    public static String parseGender(byte b){
        if(b==2){
            return "女";
        }
        else if(b==1){
            return "男";
        }
        return "神秘";
    }
}
