package com.zk;

import java.util.*;

import org.springframework.util.CollectionUtils;

/**
 * 分布式进程消费共享消息
 *
 * @author coshaho
 */
public class DistributeCache {
    private static List<String> msgCache = new ArrayList<>();

    static class MsgConsumer extends Thread {
        @Override
        public void run() {
            while (!CollectionUtils.isEmpty(msgCache)) {
                String lock = ZookeeperLock.getInstance().getLock();
                if (CollectionUtils.isEmpty(msgCache)) {
                    return;
                }
                String msg = msgCache.get(0);
                System.out.println(Thread.currentThread().getName() + " consume msg: " + msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                msgCache.remove(msg);
                ZookeeperLock.getInstance().releaseLock(lock);
            }
        }
    }

    public static void main(String[] args) {
//        for(int i = 0; i < 100; i++)
//        {
//            msgCache.add("msg" + i);
//        }
//        MsgConsumer consumer1 = new MsgConsumer();
//        MsgConsumer consumer2 = new MsgConsumer();
//        MsgConsumer consumer3 = new MsgConsumer();
//        MsgConsumer consumer4 = new MsgConsumer();
//        consumer1.start();
//        consumer2.start();
//        consumer3.start();
//        consumer4.start();

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 0);
        map.put(2, 0);

        long time = System.currentTimeMillis();
        int cnt = 0;
        int key = 3;
        int value = 1;
        while (cnt++ < 10000000) {
            map.put(key, map.getOrDefault(key, 0) + value);
            map.merge(key, value, Integer::sum);
        }
        System.out.println(System.currentTimeMillis() - time);


//        map.keySet().stream().map(integer -> "key = " + integer).forEach(System.out::println);
        map.forEach((k, v) -> System.out.println(k + ":" + v));
//
//        map.keySet().stream().map(key -> "key = " + key + " value = " + map.get(key)).forEachOrdered(System.out::println);
    }
}
