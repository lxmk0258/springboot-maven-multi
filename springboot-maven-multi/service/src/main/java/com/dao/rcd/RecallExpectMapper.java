package com.dao.rcd;


import com.bean.rcd.RecallExpect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuxin
 */
@Mapper
public interface RecallExpectMapper {
    @Select("SELECT * FROM recall_expect_v3_0 WHERE expect_id > #{expectId} and status != 1 and fresh_graduate < 2 and position < 10000000 ORDER BY expect_id LIMIT 1000")
    List<RecallExpect> selectExpectById(long expectId);

}