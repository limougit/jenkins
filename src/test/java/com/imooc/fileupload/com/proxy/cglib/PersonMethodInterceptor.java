package com.imooc.fileupload.com.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PersonMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName() + "方法调用前");
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println(method.getName() + "方法调用后");
        return o1;
    }
}
