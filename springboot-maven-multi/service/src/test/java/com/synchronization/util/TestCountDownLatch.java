package com.synchronization.util;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        // 需要等待两个线程，所以传入参数为2
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1号选手准备就绪！用时1秒！");
            latch.countDown();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2号选手准备就绪！用时2秒！");
            latch.countDown();
        }).start();

        System.out.println("请1号选手和2号选手各就各位！");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("裁判发枪，1号选手和2号选手开跑！");
    }
}
