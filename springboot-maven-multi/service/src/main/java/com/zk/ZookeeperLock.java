package com.zk;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

/**
 * zookeeper分布式共享锁
 * @author coshaho
 *
 */
class ZookeeperLock
{
    private final String ROOT_LOCK_PATH = "/Locks";
    private static ZookeeperLock lock;
    static ZookeeperLock getInstance()
    {
        if(null == lock)
        {
            lock = new ZookeeperLock();
        }
        return lock;
    }

    /**
     * 获取锁：实际上是创建线程目录，并判断线程目录序号是否最小
     * @return tag
     */
    String getLock()
    {
        try
        {
            // 关键方法，创建包含自增长id名称的目录，这个方法支持了分布式锁的实现
            // 四个参数：
            // 1、目录名称 2、目录文本信息
            // 3、文件夹权限，Ids.OPEN_ACL_UNSAFE表示所有权限
            // 4、目录类型，CreateMode.EPHEMERAL_SEQUENTIAL表示会在目录名称后面加一个自增加数字
            String preLockName = "mylock_";
            String lockPath = getZkClient().create(
                    ROOT_LOCK_PATH + '/' + preLockName,
                    Thread.currentThread().getName().getBytes(),
                    Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + " create lock path : " + lockPath);
            tryLock(lockPath);
            return lockPath;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private boolean tryLock(String lockPath) throws KeeperException, InterruptedException
    {
        // 获取ROOT_LOCK_PATH下所有的子节点，并按照节点序号排序
        List<String> lockPaths = getZkClient().getChildren(ROOT_LOCK_PATH, false);
        Collections.sort(lockPaths);
        int index = lockPaths.indexOf(lockPath.substring(ROOT_LOCK_PATH.length() + 1));
        if (index == 0)
        {
            System.out.println(Thread.currentThread().getName() + " get lock, lock path: " + lockPath);
            return true;
        }
        else
        {
            // 创建Watcher，监控lockPath的前一个节点
            Watcher watcher = new Watcher()
            {
                @Override
                public void process(WatchedEvent event)
                {
                    // 创建的锁目录只有删除事件
                    System.out.println("Received delete event, node path is " + event.getPath());
                    synchronized (this)
                    {
                        notifyAll();
                    }
                }
            };

            String preLockPath = lockPaths.get(index - 1);
            // 查询前一个目录是否存在，并且注册目录事件监听器，监听一次事件后即删除
            Stat state = getZkClient().exists(ROOT_LOCK_PATH + "/" + preLockPath, watcher);
            // 返回值为目录详细信息
            if (state == null)
            {
                return tryLock(lockPath);
            }
            else
            {
                System.out.println(Thread.currentThread().getName() + " wait for " + preLockPath);
                synchronized (watcher)
                {
                    // 等待目录删除事件唤醒
                    watcher.wait();
                }
                return tryLock(lockPath);
            }
        }
    }

    /**
     * 释放锁：实际上是删除当前线程目录
     * @param lockPath path
     */
    void releaseLock(String lockPath)
    {
        try
        {
            getZkClient().delete(lockPath, -1);
            System.out.println("Release lock, lock path is" + lockPath);
        }
        catch (InterruptedException | KeeperException e)
        {
            e.printStackTrace();
        }
    }

    private static ZooKeeper zkClient  = null;
    private ZooKeeper getZkClient()
    {
        if(null == zkClient)
        {
            try
            {
                String zookeeperIp = "127.0.0.1:2181";
                zkClient = new ZooKeeper(zookeeperIp, 3000, null);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return zkClient;
    }
}
