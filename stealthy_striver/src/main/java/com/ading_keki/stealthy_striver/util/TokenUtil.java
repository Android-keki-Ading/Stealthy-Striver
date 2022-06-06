package com.ading_keki.stealthy_striver.util;

import com.ading_keki.stealthy_striver.common.Constants;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/6/1 17:47
 * Author: medi
 */
public class TokenUtil {
    /**
     * 登录或注册成功后,生成保持用户登录状态会话token值
     * @param timeStr
     * @param userId
     * @return
     */
    public static String getToken(String timeStr, int userId) {
        String src = timeStr + userId + getRandomNum(4);

        if (null == src || "".equals(src)) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            String result = new BigInteger(1, md.digest()).toString(16);
            if (result.length() == 31) {
                result = result + "-";
            }
            System.out.println(result);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getExpiredTime(Date updateTime){
        return new Date(updateTime.getTime() + Constants.TOKEN_EXPIRED_TIME);
    }

    /**
     * 生成指定长度的随机数
     *
     * @param length
     * @return
     */
    public static int getRandomNum(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

}
