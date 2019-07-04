package com.dao;

import com.ServiceApplication;
import com.bean.User;
import com.bean.UserAccount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class UserAccountMapperTest {

    private static Logger log = LoggerFactory.getLogger(UserAccountMapperTest.class);

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Test
    public void insertTest(){
        UserAccount account = new UserAccount();
        account.setUsername("wangjun");
        account.setPassword("123456");
        account.setUser_id(1);
        userAccountMapper.insert(account);
    }
}
