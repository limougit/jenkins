package com.imooc.fileupload.annotation;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;

import java.lang.annotation.*;

/**
 * 自定义注解实现系统日志管理
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String module() default "";
    String description() default "";
    String opration() ;
}
