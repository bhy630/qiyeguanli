package com.cc.service;

import com.cc.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll()throws Exception;
}
