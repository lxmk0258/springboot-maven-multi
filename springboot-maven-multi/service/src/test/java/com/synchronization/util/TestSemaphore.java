package com.synchronization.util;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);
        System.out.println("顾客在售票处等候中");
        new Thread(() -> {
            for(;;){
                try {
                    Thread.sleep(500);
                    semaphore.acquire();
                    System.out.println("顾客拿到门票入场！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for(int i = 0; i < 3; i++){
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("售票处第" + (i + 1) + "小时售出两张票！");
                semaphore.release();
                semaphore.release();
            }
        }).start();

    }
}
