package com.dao.rcd;


import com.bean.rcd.UserExtra;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuxin
 */
@Mapper
public interface UserExtraMapper {
    @Select("select ue.id, ue.user_id, ue.description, ue.location, ue.resume_email, ue.update_time from user_extra ue LIMIT #{start},#{num}")
    List<UserExtra> selectUserExtraByLimit(int start, int num);
}