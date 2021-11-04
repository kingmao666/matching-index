package com.kingmao.juc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadPoolDemoService implements Runnable{

    private Integer index;

    public ThreadPoolDemoService(Integer i) {
        this.index = i;
    }

    @Override
    public void run() {

        String name = Thread.currentThread().getName();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(name);
    }
}
