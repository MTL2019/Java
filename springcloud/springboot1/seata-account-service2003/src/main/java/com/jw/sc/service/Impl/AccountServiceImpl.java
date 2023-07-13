package com.jw.sc.service.Impl;

import com.jw.sc.dao.AccountDao;
import com.jw.sc.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("-----------AccountService中扣减账户开始");
        accountDao.decrease(userId,money);
        log.info("-----------AccountService中扣减账户结束");


    }
}
