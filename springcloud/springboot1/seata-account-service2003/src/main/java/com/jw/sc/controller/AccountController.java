package com.jw.sc.controller;

import com.jw.sc.entity.CommonResult;
import com.jw.sc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    public CommonResult decrease(Long userId, BigDecimal money){
        accountService.decrease(userId,money);
        return new CommonResult(200,"扣减库存成功！");
    }
}
