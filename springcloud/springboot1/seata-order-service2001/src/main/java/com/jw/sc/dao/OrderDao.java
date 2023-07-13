package com.jw.sc.dao;

import com.jw.sc.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

//@MapperScan注解只会扫描包中的接口，代替多个mapper需要扫描的情况
@Mapper //将mapper注入到Spring
public interface OrderDao {

    //1. 新建订单
    public void create(Order order);

    //2. 修改订单状态
    public void update(@Param("userId")Long userId,@Param("status")Integer status);
}
