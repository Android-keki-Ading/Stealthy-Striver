package com.ading_keki.stealthy_striver.config.annotation;

import java.lang.annotation.*;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 19:13
 * Author: medi
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToSsUser {
    String value() default "user";
}
