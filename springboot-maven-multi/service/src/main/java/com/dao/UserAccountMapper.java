package com.dao;

import com.bean.UserAccount;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface UserAccountMapper {

//    @Insert("insert into user_account (username, password, user_id) values (#{username}, #{password}, #{user.id})")
    int insert(UserAccount userAccount);

//    @Select("select * from user_account where username = #{username}")
//    @Results({
//            @Result(id=true,column="username",property="username"),
//            @Result(column="password",property="password"),
//            @Result(column="user_id",property="user",
//                    one = @One(select = "com.dao.UserMapper.selectUserById",fetchType = FetchType.LAZY))
//    })
    UserAccount selectAcountByUsername(String username);

    @Update("UPDATE  user_account SET password = #{password} WHERE username = #{username}")
    void updateUserAccount(UserAccount userAccount);
}