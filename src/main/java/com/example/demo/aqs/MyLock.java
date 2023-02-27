package com.example.demo.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 一：首先创建一个类实现Lock接口，它有6个方法需要实现
 * 利用AQS实现自定义锁
 * lock()：加锁（不成功进入阻塞队列等待）
 * lockInterruptibly()：是否加锁可打断
 * tryLock()：//加锁(不成功不会进入阻塞队列等待，可以去做其他事情)
 * tryLock(long time，TimeUnit unit)：加锁（规定时间内未获得则放弃加锁）
 * unlock()：释放锁
 * newCondition()：创建条件变量
 * 二：创建一个内部类，继承AbstractQueuedSynchronizer
 *
 * 可以根据需求重写具体方法，总共有5种方法
 * isHeldExclusively()：该线程是否正在独占资源。只有用到condition才需要去实现它。
 * tryAcquire(int)：独占方式。尝试获取资源，成功则返回true，失败则返回false。
 * tryRelease(int)：独占方式。尝试释放资源，成功则返回true，失败则返回false。
 * tryAcquireShared(int)：共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
 * tryReleaseShared(int)：共享方式。尝试释放资源，如果释放后允许唤醒后续等待结点返回true，否则返回false。
 * ————————————————
 * 三：我需要自定义一个独占锁，不可重入，具有变量条件的锁
 * 独占锁：AQS同步器中需要重写独占方式的获取资源tryAcquire(int)和释放资源tryRelease(int)方法
 * 不可重入：AQS同步器需要实现isHeldExclusively()：
 * 具有条件变量：AQS同步器中 return new ConditionObject();
 */
public class MyLock implements Lock {

    class MySync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                System.out.println(Thread.currentThread().getName()+"获取锁成功");
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }
        public Condition newCondition(){
            return new ConditionObject();
        }
    }

    private MySync mySync = new MySync();
    @Override
    public void lock() {
        mySync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        mySync.acquireInterruptibly(1);

    }

    @Override
    public boolean tryLock() {
        return mySync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return mySync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        mySync.release(1);
    }

    @Override
    public Condition newCondition() {
        return mySync.newCondition();
    }
}
