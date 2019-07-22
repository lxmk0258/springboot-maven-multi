package com.dao;

import com.ServiceApplication;
import com.bean.local.User;
import com.bean.local.UserAccount;
import com.dao.local.UserAccountMapper;
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
        account.setUsername("chutian1");
        account.setPassword("123456");
        User user = new User();
        user.setId(5);
        user.setAge(10);
        user.setName("雏田");
        user.setComment("太子妃");
        account.setUser(user);
        userAccountMapper.insert(account);
    }

    @Test
    public void selectAcountByUsernameTest(){
        UserAccount account = userAccountMapper.selectAcountByUsername("wangjun");
        Assert.assertEquals("王军", account.getUser().getName());
    }
}
