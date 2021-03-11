package com.cc.dao;

import com.cc.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleDao {

    //根据用户Id查询
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.cc.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleUserId(int userId) throws Exception;

    //查询所有
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    //添加
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;
}
