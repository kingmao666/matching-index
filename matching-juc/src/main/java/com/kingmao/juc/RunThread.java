package com.kingmao.juc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunThread {
    public static void main(String[] args) {
        Thread t = new Thread("sdf"){
            @Override
            public void run() {
                log.info("ddd");
                log.error("fff");
            }
        };
        t.start();
    }
}
