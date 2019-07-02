package com.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.service.DubboService;

public class StaticDubboServiceImpl implements DubboService {
    @Override
    public boolean login(String name, String password) {
        return (name+"123").equals(password);
    }
}
