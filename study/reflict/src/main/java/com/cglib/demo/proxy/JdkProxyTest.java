package com.cglib.demo.proxy;

import com.cglib.demo.proxy.impl.UserServiceImpl;
import com.cglib.demo.proxy.service.UserService;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

public class JdkProxyTest implements InvocationHandler {

    /**
     * 需代理的对象
     */
    private Object target;

    public JdkProxyTest(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理对象调用方法开始...");
        Object obj = method.invoke(target, args);
        System.out.println("代理对象调用方法结束...");
        return obj;
    }


    /**
     * 被代理对象赋值，并返回代理对象jdkProxy
     * @return
     */
    private Object getTargetInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    public static void main(String[] args) {
        JdkProxyTest proxy = new JdkProxyTest(new UserServiceImpl());
        UserService service = (UserService) proxy.getTargetInstance();
        service.addUser("张三", "123455");
        service.delUser("李四");
    }
}
