package com.dao;

import com.ServiceApplication;
import com.bean.rcd.RecallExpect;
import com.dao.rcd.RecallExpectMapper;
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
public class RecallExpectMapperTest {

    private static Logger log = LoggerFactory.getLogger(RecallExpectMapperTest.class);

    @Autowired
    private RecallExpectMapper recallExpectMapper;

    @Test
    public void selectExpectByIdTest() {
        List<RecallExpect> recallExpects = recallExpectMapper.selectExpectById(0);
        for (RecallExpect recallExpect : recallExpects) {
            log.info(recallExpect.getName());
        }
    }
}
