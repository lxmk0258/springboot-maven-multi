package com.dao;

import com.ServiceApplication;
import com.bean.Article;
import com.bean.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class ArticleMapperTest {

    private static Logger log = LoggerFactory.getLogger(ArticleMapperTest.class);

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void insertTest(){
        Article article = new Article();
        article.setArticle_name("iphone");
        article.setArticle_num(10);
        article.setArticle_price(5499.0);
        article.setAdd_time(new Date());
        article.setUpdate_time(new Date());
        articleMapper.insert(article);
    }

//    @Test
//    public void selectAcountByUsernameTest(){
//        UserAccount account = userAccountMapper.selectAcountByUsername("wangjun");
//        Assert.assertEquals("王军", account.getUser().getName());
//    }
}
