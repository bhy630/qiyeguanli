package com.cc.dao;

import com.cc.domain.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId(int id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;
}
