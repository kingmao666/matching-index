package com.kingmao.juc;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();




        for (int i = 0; i < 100; i++) {
            ThreadPoolDemoService threadPoolDemoService = new ThreadPoolDemoService(i);
            //executorService.submit(threadPoolDemoService);
            executorService.submit(threadPoolDemoService);
        }
    }
}
