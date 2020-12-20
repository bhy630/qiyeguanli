package com.donkee.house.dao;

import com.donkee.house.entity.House;
import com.donkee.house.util.PageInfo;

public interface IHouseDao extends IBaseDao<House> {
    PageInfo<House> findByCondition(int pageNum,int pageSize,House house) throws Exception;
}
