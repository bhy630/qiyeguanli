package com.cc.service;

import com.cc.domain.Role;
import com.cc.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(int id)throws Exception;

    List<Role> findOtherRole(int userId) throws Exception;

    void addRoleToUser(int userId, int[] roleIds)throws Exception;
}
