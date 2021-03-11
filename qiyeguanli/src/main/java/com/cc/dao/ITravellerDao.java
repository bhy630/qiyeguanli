package com.cc.dao;

import com.cc.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ITravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    List<Traveller> findByOrdersId(int ordersId) throws Exception;
}
