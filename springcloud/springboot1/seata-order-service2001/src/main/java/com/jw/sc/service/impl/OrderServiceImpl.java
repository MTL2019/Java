package com.jw.sc.service.impl;

import com.jw.sc.dao.OrderDao;
import com.jw.sc.domain.Order;
import com.jw.sc.service.AccountService;
import com.jw.sc.service.OrderService;
import com.jw.sc.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    //开启全局事务，在开启全局事务入口加注解
    @Override
    @GlobalTransactional(name = "fsp.create.order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("---------->  开始新建订单--");
        orderDao.create(order);

        log.info("---------->  订单微服务调用库存，做扣减Count--");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("---------->  订单微服务调用库存，做扣减Count--end");

        log.info("---------->  订单微服务调用账户，做扣减Money--");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("---------->  订单微服务调用账户，做扣减Money--end");

        //修改订单状态，从0到1；1为订单完成
        log.info("---------->  开始修改订单状态--");
        orderDao.update(order.getUserId(),0);
        log.info("---------->  修改订单状态--end");

        log.info("---------->  下订单结束了--end");
    }
}
