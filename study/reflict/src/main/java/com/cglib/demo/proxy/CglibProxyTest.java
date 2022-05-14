package com.cglib.demo.proxy;

import com.cglib.demo.proxy.impl.AgentService;
import com.cglib.demo.proxy.impl.BrmsAgentService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 若不使用匿名内部类则直接继承MethodInterceptor方法实现intercept即可
 */
public class CglibProxyTest {
    public static void main(String[] args) {

        //创建被代理对象
        final BrmsAgentService target = new BrmsAgentService();

        Enhancer enhancer = new Enhancer();
        //调用方法前增强处理
        enhancer.setSuperclass(new BrmsAgentService().getClass());
        enhancer.setCallback(new MethodInterceptor() {
            /*Object o：代理对象本身
              Method method:被调用方法
              Object[] objects：传参
              methodProxy：调用方法代理对象
            */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if ("addAgent".equals(method.getName())) {
                    System.out.println("代理对象添加用户");
                } else {
                    System.out.println("代理对象删除用户");
                }
                //Object object = method.invoke(target, objects);
                Object object = methodProxy.invokeSuper(o, objects);
                return object;
            }
        });


        AgentService agentService = (BrmsAgentService) enhancer.create();
        agentService.addAgent("张三");
        agentService.deleteAgent("李四");
    }
}
