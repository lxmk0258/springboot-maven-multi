package com.dao;

import com.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 通过名字查询用户信息
     */
//    @Select("SELECT * FROM recall_expect_v3_0 WHERE id = #{id}")
    User findUserById(@Param("id") int id);

//    /**
//     * 查询所有用户信息
//     */
//    @Select("SELECT * FROM user")
//    List<User> findAllUser();
//
    /**
     * 插入用户信息
     */
    @Insert("INSERT INTO user_info (name, age,money ,comment) VALUES (#{name}, #{age}, #{money} ,#{comment})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertUser(User user);
//
//    /**
//     * 根据 id 更新用户信息
//     */
//    @Update("UPDATE  user SET name = #{name},age = #{age},money= #{money} WHERE id = #{id}")
//    void updateUser(@Param("name") String name, @Param("age") Integer age, @Param("money") Double money,
//                    @Param("id") int id);
//
//    /**
//     * 根据 id 删除用户信息
//     */
//    @Delete("DELETE from user WHERE id = #{id}")
//    void deleteUser(@Param("id") int id);
}
