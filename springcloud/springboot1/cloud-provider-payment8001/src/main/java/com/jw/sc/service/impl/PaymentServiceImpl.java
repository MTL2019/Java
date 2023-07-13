package com.jw.sc.service.impl;

import com.jw.sc.dao.PaymentDao;
import com.jw.sc.entity.Payment;
import com.jw.sc.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService{

    //@Autowired只按照byType 注入；@Resource默认按byName自动注入，也提供按照byType 注入；
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
