package com.test;

import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestSynchronize implements Runnable {
    private static Map<Integer, MyCount> map = new ConcurrentHashMap<>();
    private int num;
    private static int cnt;

    public TestSynchronize(int num) {
        this.num = num;
    }

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            map.computeIfAbsent(i, k -> new MyCount());
//        }
//
//
//        for (int i = 0; i < 1000; i++) {
//            new Thread(new TestSynchronize(i % 10)).start();
//        }
//        Thread.sleep(2000);
//
//        map.forEach((k, v) -> System.out.println(k + ":" + v.count));
//        System.out.println(cnt);

//        Map<Integer, Integer> map1 = new HashMap<>();
//        map1.putIfAbsent(1, 3);
//        map1.putIfAbsent(1, 2);
//        map1.forEach((k, v) -> System.out.println(k + ":" + v));

        Map<Integer ,String> map = Collections.synchronizedMap( new LinkedHashMap<Integer ,String>(100, 0.75F,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 10;
            }
        });

        map.put(1,"1");
        map.put(2,"1");
        map.put(3,"1");
        map.put(4,"1");
        map.put(5,"1");
        map.put(6,"1");
        map.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("=============");
        map.get(1);

        map.get(3);

        map.forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("=============");

        map.get(2);

        map.put(7,"1");
        map.put(8,"1");
        map.put(9,"1");
        map.put(10,"1");
        map.put(11,"1");

        map.forEach((k, v) -> System.out.println(k + ":" + v));


    }

    @Override
    public void run() {
        MyCount myCount = map.get(num);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (TestSynchronize.class){
                myCount.count++;
                cnt++;
            }

        }
    }

    static class MyCount {
        public int count = 0;
    }
}
