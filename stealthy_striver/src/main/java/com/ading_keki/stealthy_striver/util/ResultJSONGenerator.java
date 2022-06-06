package com.ading_keki.stealthy_striver.util;

import org.springframework.util.StringUtils;

/**
 * @project: stealthy_striver
 * @Created-Time: 2022-04-07 19:18
 * @Author: Ading
 * @file_desc:
 */
public class ResultJSONGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_SERVER_ERROR = 500;

    public static ResultJSON getSuccessResultJSON() {
        ResultJSON result = new ResultJSON();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return result;
    }

    public static ResultJSON getSuccessResultJSON(String message){
        ResultJSON resultJSON = new ResultJSON();
        resultJSON.setResultCode(RESULT_CODE_SUCCESS);
        resultJSON.setMessage(message);
        return resultJSON;
    }

    public static ResultJSON getSuccessResultJSON(Object data){
        ResultJSON resultJSON = new ResultJSON();
        resultJSON.setResultCode(RESULT_CODE_SUCCESS);
        resultJSON.setMessage(DEFAULT_SUCCESS_MESSAGE);
        resultJSON.setData(data);
        return resultJSON;
    }

    public static ResultJSON getFailResultJSON(){
        ResultJSON resultJSON = new ResultJSON();
        resultJSON.setResultCode(RESULT_CODE_SERVER_ERROR);
        resultJSON.setMessage(DEFAULT_FAIL_MESSAGE);
        return resultJSON;
    }

    public static ResultJSON getFailResultJSON(String message){
        ResultJSON resultJSON = new ResultJSON();
        resultJSON.setResultCode(RESULT_CODE_SERVER_ERROR);
        resultJSON.setMessage(message);
        return resultJSON;
    }

    public static ResultJSON getErrorResultJSON(String message){
        ResultJSON resultJSON = new ResultJSON();
        resultJSON.setMessage(message);
        resultJSON.setResultCode(RESULT_CODE_SUCCESS);
        return resultJSON;
    }
}
