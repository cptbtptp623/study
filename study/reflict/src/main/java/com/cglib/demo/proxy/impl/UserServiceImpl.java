package com.cglib.demo.proxy.impl;

import com.cglib.demo.proxy.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public void addUser(String userName, String password) {
        System.out.println("添加用户");
        System.out.println("用户名是：" + userName);
        System.out.println("密码是：" + password);
    }

    @Override
    public void delUser(String userName) {
        System.out.println("删除用户，用户名是：" + userName);
    }
}
