package com.cc.dao;

import com.cc.domain.Member;
import com.cc.domain.Orders;
import com.cc.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface IOrdersDao {

    //查询所有订单信息
    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId", javaType = Product.class, one = @One(select = "com.cc.dao.IProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;

    //查询订单详情
    @Select("select * from orders where id =#{ordersId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId", javaType = Product.class, one = @One(select = "com.cc.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class, one = @One(select = "com.cc.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = List.class, many = @Many(select = "com.cc.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(int ordersId) throws Exception;
}
