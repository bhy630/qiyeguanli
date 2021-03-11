package com.cc.dao;

import com.cc.domain.Role;
import com.cc.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface IUserDao {


    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.cc.dao.IRoleDao.findRoleUserId"))
    })
    UserInfo findByUsername(String username)throws Exception;


    //添加用户
    @Insert("insert into users(username,password,phoneNum,status,email) values(#{username},#{password},#{phoneNum},#{status},#{email})")
    void save(UserInfo userInfo) throws Exception;

    //查询用户
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.cc.dao.IRoleDao.findRoleUserId"))
    })
    UserInfo findById(int id);

    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRole(int userId);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") int userId,@Param("roleId") int roleId);
}
