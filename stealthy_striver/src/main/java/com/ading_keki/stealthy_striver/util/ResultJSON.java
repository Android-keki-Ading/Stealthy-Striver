package com.ading_keki.stealthy_striver.util;

import java.io.Serializable;

/**
 * @project: stealthy_striver
 * @Created-Time: 2022-04-07 18:37
 * @Author: Ading
 * @file_desc:
 */
public class ResultJSON<T> implements Serializable {
    private int resultCode;
    private T data;//数据
    private String message;//信息

    public ResultJSON() {
    }

    public ResultJSON(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
