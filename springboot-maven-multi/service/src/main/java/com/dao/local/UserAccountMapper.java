package com.dao.local;

import com.bean.local.UserAccount;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserAccountMapper {

//    @Insert("insert into user_account (username, password, user_id) values (#{username}, #{password}, #{user.id})")
    int insert(UserAccount userAccount);

//    @Select("select * from user_account where username = #{username}")
//    @Results({
//            @Result(id=true,column="username",property="username"),
//            @Result(column="password",property="password"),
//            @Result(column="user_id",property="user",
//                    one = @One(select = "com.dao.local.UserMapper.selectUserById",fetchType = FetchType.LAZY))
//    })
    UserAccount selectAcountByUsername(String username);

    @Update("UPDATE  user_account SET password = #{password} WHERE username = #{username}")
    void updateUserAccount(UserAccount userAccount);
}