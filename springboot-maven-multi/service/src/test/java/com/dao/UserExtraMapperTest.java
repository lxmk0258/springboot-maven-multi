package com.dao;

import com.ServiceApplication;
import com.bean.rcd.UserExtra;
import com.dao.rcd.UserExtraMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class UserExtraMapperTest {

    private static Logger log = LoggerFactory.getLogger(UserExtraMapperTest.class);

    @Autowired
    private UserExtraMapper userExtraMapper;

    @Test
    public void selectExpectByIdTest() {
        List<UserExtra> userExtras = userExtraMapper.selectUserExtraByLimit(10,5);
        for (UserExtra userExtra : userExtras) {
            log.info(userExtra.getId()+" : "+userExtra.getDescription());
        }
    }
}
