package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.service.DubboService;

@Service
public class DefaultDubboServiceImpl implements DubboService {

    @Override
    public boolean login(String name, String password) {
        System.out.println(name + " login");
        return (name + "123").equals(password);
    }
}
