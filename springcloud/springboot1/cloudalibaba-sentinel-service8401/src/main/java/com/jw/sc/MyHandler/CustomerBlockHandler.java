package com.jw.sc.MyHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jw.sc.entity.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult HandlerException(BlockException exception)
    {
        return new CommonResult(444,"按客户自定义, global handler,HandlerException --- 1");
    }

    public static CommonResult HandlerException2(BlockException exception)
    {
        return new CommonResult(444,"按客户自定义, global handler,HandlerException --- 2 ");
    }
}
