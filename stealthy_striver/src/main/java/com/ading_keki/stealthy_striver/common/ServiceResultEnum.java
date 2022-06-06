package com.ading_keki.stealthy_striver.common;

public enum ServiceResultEnum {
    ERROR("error"),

    LOGIN_NAME_IS_INVALID("请输入正确的手机号/邮箱！"),

    REGISTER_SUCCESS("注册成功!"),

    LOGIN_NAME_NULL("请输入登录名！"),

    LOGIN_PASSWORD_NULL("请输入密码！"),

    LOGIN_VERIFY_CODE_NULL("请输入验证码！"),

    LOGIN_VERIFY_CODE_ERROR("验证码错误！"),

    PARAM_ERROR("参数错误！"),

    SAME_LOGIN_NAME_EXIST("用户名已存在！"),

    DATA_NOT_EXIST("未查询到记录！"),

    LOGIN_ERROR("登录失败！"),

    NOT_LOGIN_ERROR("未登录！"),

    UPDATE_ERROR("修改失败！"),

    UPDATE_SUCCESS("修改成功！"),

    USER_NULL_ERROR("无效用户！请重新登录！"),

    OPERATE_ERROR("操作失败！"),

    NO_PERMISSION_ERROR("无权限！"),

    TOKEN_EXPIRE_ERROR("无效认证！请重新登录！"),

    DB_ERROR("database error"),

    NULL_PASSWORD("密码不能设置为空！");


    private String description;

    ServiceResultEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
