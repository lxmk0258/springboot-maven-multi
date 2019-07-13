package com.zk;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * 分布式进程消费共享消息
 * @author coshaho
 *
 */
public class DistributeCache
{
    private static List<String> msgCache = new ArrayList<String>();

    static class MsgConsumer extends Thread
    {
        @Override
        public void run()
        {
            while(!CollectionUtils.isEmpty(msgCache))
            {
                String lock = ZookeeperLock.getInstance().getLock();
                if(CollectionUtils.isEmpty(msgCache))
                {
                    return;
                }
                String msg = msgCache.get(0);
                System.out.println(Thread.currentThread().getName() + " consume msg: " + msg);
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                msgCache.remove(msg);
                ZookeeperLock.getInstance().releaseLock(lock);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 100; i++)
        {
            msgCache.add("msg" + i);
        }
        MsgConsumer consumer1 = new MsgConsumer();
        MsgConsumer consumer2 = new MsgConsumer();
        MsgConsumer consumer3 = new MsgConsumer();
        MsgConsumer consumer4 = new MsgConsumer();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
    }
}
