package com.example.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {
    public static void main(String[] args) {
        UserService target = new UserService();
        UserInterface userInterface = (UserInterface) Proxy.newProxyInstance(UserInterface.class.getClassLoader(), new Class[]{UserInterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before....");
                Object result = method.invoke(target,args);
                System.out.println("after...");
                return result;
            }
        });
        userInterface.test();
    }
}
