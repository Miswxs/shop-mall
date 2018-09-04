package com.lj.common.annotation;

import java.lang.annotation.*;

/**
 * 登录标识注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {

    String[] value() default {};

    String[] permissions() default {};

    boolean requirePerm() default false;

    boolean isExcelExport() default false;

}
