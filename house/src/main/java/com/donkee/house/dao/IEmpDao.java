package com.donkee.house.dao;

import com.donkee.house.entity.Emp;

public interface IEmpDao extends IBaseDao<Emp> {
    Emp findByName(String ename) throws Exception;
}
