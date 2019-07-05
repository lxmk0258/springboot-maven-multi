package com.dao;

import com.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface UserMapper {

//    @Select("SELECT * FROM user_info WHERE id = #{id}")
//    @Results(value = {
//            @Result(id = true, column = "id", property = "id"),
//            @Result(column = "name", property = "name"),
//            @Result(column = "age", property = "age"),
//            @Result(column = "money", property = "money"),
//            @Result(column = "comment", property = "comment"),
//            @Result(column = "id", property = "orderList",
//                    many = @Many(select = "com.dao.OrderMapper.selectOrderByUserId", fetchType = FetchType.LAZY))
//    })
    User selectUserById(@Param("id") int id);

    @Select("SELECT * FROM user_info")
    List<User> selectAllUser();

    @Insert("INSERT INTO user_info (name, age,money ,comment) VALUES (#{name}, #{age}, #{money} ,#{comment})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(User user);

    @Update("UPDATE  user_info SET name = #{name},age = #{age},money= #{money},comment= #{comment} WHERE id = #{id}")
    void updateUser(User user);

    @Delete("DELETE from user_info WHERE id = #{id}")
    void deleteUser(@Param("id") int id);
}
