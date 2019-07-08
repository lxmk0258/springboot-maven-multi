package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bean.User;
import com.bean.UserAccount;
import com.dao.UserAccountMapper;
import com.dao.UserMapper;
import com.dubbo.service.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DefaultDubboServiceImpl implements DubboService {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultDubboServiceImpl.class);

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public boolean login(String username, String password) {

        UserAccount userAccount = userAccountMapper.selectAcountByUsername(username);
        LOGGER.info(userAccount.getUser().getName() + " login");
        return password.equals(userAccount.getPassword());
    }
}
