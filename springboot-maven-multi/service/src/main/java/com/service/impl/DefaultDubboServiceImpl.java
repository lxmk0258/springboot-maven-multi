package com.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.service.DubboService;

@Service
public class DefaultDubboServiceImpl implements DubboService {
    @Override
    public boolean login(String name, String password) {
        return !name.equals(password);
    }
}
