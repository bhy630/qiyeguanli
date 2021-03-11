package com.donkee.house.dao;

import com.donkee.house.entity.Custom;

import java.util.List;

public interface ICustomDao extends IBaseDao<Custom> {

    List<Custom> listName() throws Exception;
}
