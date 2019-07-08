package com.dao;

import com.bean.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO order_info ( user_id, order_price, order_status, add_time, update_time) " +
            "VALUES ( #{user.id}, #{order_price}, #{order_status}, #{add_time}, #{update_time})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Order record);

    @Select("SELECT * FROM order_info WHERE user_id = #{id}")
    List<Order> selectOrderByUserId(int id);

    @Select("SELECT * FROM order_info WHERE id = #{id}")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "order_price", property = "order_price"),
            @Result(column = "order_status", property = "order_status"),
            @Result(column = "add_time", property = "add_time"),
            @Result(column = "update_time", property = "update_time"),
            @Result(column = "user_id", property = "user",
                    one = @One(select = "com.dao.UserMapper.selectUserById", fetchType = FetchType.LAZY)),
            @Result(column = "id", property = "articleList",
                    many = @Many(select = "com.dao.ArticleMapper.selectArticleByOrderId", fetchType = FetchType.LAZY))
    })
    Order selectOrderById(int id);


    int orderArticle(int orderId, Map<Integer,Integer> articleAmout);

}