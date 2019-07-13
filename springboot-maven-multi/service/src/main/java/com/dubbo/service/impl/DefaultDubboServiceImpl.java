package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bean.UserAccount;
import com.dao.UserAccountMapper;
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
    public String login(String username, String password) {

        UserAccount userAccount = userAccountMapper.selectAcountByUsername(username);
        if (userAccount == null || !password.equals(userAccount.getPassword())) {
            return null;
        } else {
            return userAccount.getUser().getName();
        }
    }
}
