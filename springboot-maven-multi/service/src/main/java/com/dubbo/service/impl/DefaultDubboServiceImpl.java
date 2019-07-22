package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bean.local.UserAccount;
import com.bean.rcd.RecallExpect;
import com.dao.local.UserAccountMapper;
import com.dao.rcd.RecallExpectMapper;
import com.dubbo.service.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DefaultDubboServiceImpl implements DubboService {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultDubboServiceImpl.class);

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private RecallExpectMapper recallExpectMapper;

    @Override
    public String login(String username, String password) {

        List<RecallExpect> recallExpects = recallExpectMapper.selectExpectById(0);
        for (RecallExpect recallExpect : recallExpects) {
            LOGGER.info(recallExpect.getName());
        }

        UserAccount userAccount = userAccountMapper.selectAcountByUsername(username);
        if (userAccount == null || !password.equals(userAccount.getPassword())) {
            return null;
        } else {
            return userAccount.getUser().getName();
        }
    }
}
