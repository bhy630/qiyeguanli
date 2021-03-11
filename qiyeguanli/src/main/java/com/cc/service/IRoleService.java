package com.cc.service;

import com.cc.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;
}
