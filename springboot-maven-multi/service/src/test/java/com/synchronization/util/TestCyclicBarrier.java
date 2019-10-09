package com.synchronization.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
    // 1号选手跑的轮数
    public static int countA = 1;
    // 2号选手跑的轮数
    public static int countB = 1;

    public static void main(String[] args) {
        // 填入2,代表2个线程互相等待
        CyclicBarrier barrier = new CyclicBarrier(2);
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("1号选手开始跑！当前第" + countA++ + "轮比赛！");
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("1号选手抵达终点！");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("2号选手开始跑！当前第" + countB++ + "轮比赛！");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("2号选手抵达终点！");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
