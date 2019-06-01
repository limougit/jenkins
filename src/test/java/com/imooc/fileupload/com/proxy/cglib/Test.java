package com.imooc.fileupload.com.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class Test {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new PersonMethodInterceptor());
        Person person = (Person) enhancer.create();
        person.say();

    }
}
