package com.jw.sc.controller;

import com.jw.sc.entity.CommonResult;
import com.jw.sc.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;

    public CommonResult decrease(Long productId, Integer count){
        storageService.decrease(productId,count);
        return new CommonResult(200,"扣减库存成功！");
    }
}
