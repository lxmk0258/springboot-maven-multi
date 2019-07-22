package com.dao;

import com.ServiceApplication;
import com.bean.local.Order;
import com.bean.local.User;
import com.dao.local.OrderMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class OrderMapperTest {

    private static Logger log = LoggerFactory.getLogger(OrderMapperTest.class);

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertTest() {
        User user = new User();
        user.setId(2);
        Order order = new Order();
        order.setUser(user);
        order.setOrder_price(1000.5);
        order.setOrder_status("0");
        order.setAdd_time(new Date());
        order.setUpdate_time(new Date());
        orderMapper.insert(order);
    }

    @Test
    public void selectOrderByIdTest() {
        Order order = orderMapper.selectOrderById(3);
        Assert.assertEquals("电脑", order.getArticleList().get(0).getArticle_name());
        Assert.assertEquals("鸣人", order.getUser().getName());
    }

    @Test
    public void orderArticleTest() {
        Map<Integer,Integer> map = new HashMap<>(2);
        map.put(1,3);
        map.put(3,7);
        int num = orderMapper.orderArticle(3, map);
        Assert.assertEquals(2, num);
    }
}
