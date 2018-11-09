package com.work.serviceali.service.impl;

import org.springframework.beans.factory.annotation.Value;

public class dd {

    @Value("${asdk.tradePay}")
    static
    String asdk_tradePay;

    public static void main(String[] args) {
        System.out.println(asdk_tradePay);
    }

}
