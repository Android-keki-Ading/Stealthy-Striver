package com.ading_keki.stealthy_striver.config.handler;

import com.ading_keki.stealthy_striver.common.Constants;
import com.ading_keki.stealthy_striver.common.ServiceResultEnum;
import com.ading_keki.stealthy_striver.config.ExceptionConfig;
import com.ading_keki.stealthy_striver.config.annotation.TokenToSsUser;
import com.ading_keki.stealthy_striver.mapper.SsUserMapper;
import com.ading_keki.stealthy_striver.mapper.SsUserTokenMapper;
import com.ading_keki.stealthy_striver.pojo.SsUser;
import com.ading_keki.stealthy_striver.pojo.SsUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/6/4 18:45
 * Author: medi
 */

@Component
public class TokenToSsUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private SsUserMapper ssUserMapper;

    @Autowired
    private SsUserTokenMapper ssUserTokenMapper;

    public TokenToSsUserMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToSsUser.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        if (parameter.getParameterAnnotation(TokenToSsUser.class) instanceof TokenToSsUser) {
            SsUser ssUser = null;
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == Constants.TOKEN_LENGTH) {
                SsUserToken ssUserToken = ssUserTokenMapper.selectByToken(token);
                if (ssUserToken == null || ssUserToken.getExpireTime().getTime() <= System.currentTimeMillis()) {
                    ExceptionConfig.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getDescription());
                }
                ssUser = ssUserMapper.selectUserById(ssUserToken.getUserId());
                if (ssUser == null) {
                    ExceptionConfig.fail(ServiceResultEnum.USER_NULL_ERROR.getDescription());
                }
                return ssUser;
            } else {
                ExceptionConfig.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getDescription());
            }
        }
        return null;
    }
}
