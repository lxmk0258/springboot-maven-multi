package com.dao;

import com.ServiceApplication;
import com.bean.local.User;
import com.dao.local.UserMapper;
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
public class UserMapperTest {

    private static Logger log = LoggerFactory.getLogger(UserMapperTest.class);

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectUserByIdTest(){
        User user = userMapper.selectUserById(1);
        Assert.assertEquals(2, user.getOrderList().size());
    }

    @Test
    public void insertUserTest(){
        User user = new User();
        user.setName("佐助");
        user.setAge(23);
        user.setMoney(10000.11);
        user.setComment("好基友");
        userMapper.insertUser(user);
    }
}
