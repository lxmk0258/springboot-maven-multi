package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bean.User;
import com.dao.UserDao;
import com.dubbo.service.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DefaultDubboServiceImpl implements DubboService {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultDubboServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(Long id, String password) {

        User user = userDao.findUserById(id);
        LOGGER.info(user.getName() + " login");
        return password.equals(String.valueOf(user.getGeek_id()));
    }
}
