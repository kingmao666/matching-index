package com.kingmao.juc.createthread;

public class RunThread {
    public static void main(String[] args) {
        Thread t = new Thread("tttt1"){
            @Override
            public void run() {
                System.out.println("say hello");
            }
        };
        t.start();
    }
}
