package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bean.User;
import com.dao.UserMapper;
import com.dubbo.service.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DefaultDubboServiceImpl implements DubboService {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultDubboServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(int id, String password) {

        User user = userMapper.selectUserById(id);
        LOGGER.info(user.getName() + " login");
        return password.equals(String.valueOf(user.getId()));
    }
}
