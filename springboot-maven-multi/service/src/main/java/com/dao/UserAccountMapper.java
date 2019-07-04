package com.dao;

import com.bean.UserAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountMapper {

    int insert(UserAccount userAccount);
}