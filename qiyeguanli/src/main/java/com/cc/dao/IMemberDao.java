package com.cc.dao;

import com.cc.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    //根据ID查询
    @Select("select * from member where id = #{memberId}")
    Member findById(int memberId) throws Exception;
}
