package com.imooc.fileupload.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imooc.fileupload.annotation.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Iterator;

@Component
@Aspect
public class SystemLog {
    private static final Logger log = LoggerFactory.getLogger(SystemLog.class);

    @Pointcut("@annotation(com.imooc.fileupload.annotation.SysLog)")
    public void executeService(){

    };

    @Before("executeService()")
    public void doBefor(JoinPoint point){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpRequest = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        log.info("访问主机地址：" + httpRequest.getRemoteAddr() +"========" + httpRequest.getRemoteHost());
        log.info(JSON.toJSONString(httpRequest.getParameterMap()));
        String jsonString = JSON.toJSONString(httpRequest.getParameterMap());
        JSONObject map = JSON.parseObject(jsonString);
        for (Iterator it = map.keySet().iterator();it.hasNext();){
            Object key = it.next();
            Object value = map.get(key);
            log.info("key:" + key + "========value:" + value);
        }


        Signature signature = point.getSignature();
        String methodName = signature.getName();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Method realMethod = null;
        try {
            realMethod = point.getTarget().getClass().getDeclaredMethod(methodName,targetMethod.getParameterTypes());
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage());
        }
        SysLog sysLogAnnotation = realMethod.getAnnotation(SysLog.class);
        log.info("获取到自定义注解的模块参数：" + sysLogAnnotation.module());
        log.info("获取到自定义注解的描述参数：" + sysLogAnnotation.description());
        log.info("获取到自定义注解的操作参数：" + sysLogAnnotation.opration());


    }

}
