package com.dao;

import com.bean.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("INSERT INTO article_info ( article_name, article_price, article_num, add_time, update_time) " +
            "VALUES (#{article_name}, #{article_price}, #{article_num}, #{add_time}, #{update_time})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Article record);

    @Select("SELECT * FROM article_info WHERE id in (SELECT article_id FROM order_article WHERE order_id = #{id})")
    List<Article> selectArticleByOrderId(int id);

    Article selectArticleById(int id);
}