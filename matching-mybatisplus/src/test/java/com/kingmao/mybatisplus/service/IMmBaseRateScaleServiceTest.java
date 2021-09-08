package com.kingmao.mybatisplus.service;

import com.kingmao.mybatisplus.BaseTestUint;
import com.kingmao.mybatisplus.entity.MmBaseRateScale;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class IMmBaseRateScaleServiceTest extends BaseTestUint {


    @Autowired
    private IMmBaseRateScaleService baseRateScaleService;

    @Test
    public void t1(){
        MmBaseRateScale byId = baseRateScaleService.getById(1);
        System.out.println(byId);
    }

}