package com.jw.sc.service.impl;

import cn.hutool.core.util.IdUtil;
import com.jw.sc.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaumentServiceImpl implements PaymentService {

    /**
     * 正常访问OK
     * @param id 输入参数id
     * @return  返回当前信息
     */
    public String paymentInfo_Ok(Integer id){
        return "线程池："+ Thread.currentThread().getName()+ ",   paymentInfo_Ok --> id:  " +id +"\t";
    }

    //服务端服务降级，绑定兜底方法即可；主程序加开启Hystrix注解
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    }) //当下面的方法超过设定的3s时间，被认为系统出问题，会自动调用fallbackMethod指定的方法
    public String paymentInfo_Timeout(Integer id){

        //int age = 10/0; //出现异常也可以识别
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        return "线程池："+ Thread.currentThread().getName()+ ", --> id:  "
                +id +"\t";
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池："+ Thread.currentThread().getName()+ ", 系统繁忙或运行出错，请稍后再试 --> id: "
                +id +"\t" + "调用兜底方法成功！";
    }

    //////////服务熔断
    //10000ms中10次访问超过60%失败，则熔断；
    //默认为20次请求，50%成功率就会打开
    //间隔一段时间后，会自动尝试恢复
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//开启熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){

        if (id < 0 ) {
            throw new RuntimeException("*****id 不能为负数");
        }

        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号： " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id")Integer id){

        return "id 不能为负数，请稍后再试。  id： " + id;
    }
}
