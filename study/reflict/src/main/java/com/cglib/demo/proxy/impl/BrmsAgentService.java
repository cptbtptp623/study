package com.cglib.demo.proxy.impl;

public class BrmsAgentService extends AgentService {
    @Override
    public void addAgent(String name) {
        System.out.println("添加代理用户名为：" + name);
    }

    @Override
    public void deleteAgent(String name) {
        System.out.println("删除代理用户名为：" + name);
    }
}
