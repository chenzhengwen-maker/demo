package com.example.demo.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Semaphore;

@RestController
@Slf4j
public class SemaphoreLimitTest {
    @Autowired
    RedissonClient redissonClient;

    private static final Semaphore semaphore = new Semaphore(5);


    /**
     * 限流，拿不到凭证的直接拒绝
     * acquire() 表示阻塞并获取许可
     * tryAcquire() 方法在没有许可的情况下会立即返回 false，要获取许可的线程不会阻塞
     * release() 表示释放许可
     * int availablePermits()：返回此信号量中当前可用的许可证数。
     * int getQueueLength()：返回正在等待获取许可证的线程数。
     * boolean hasQueuedThreads()：是否有线程正在等待获取许可证。
     * void reducePermit（int reduction）：减少 reduction 个许可证
     * Collection getQueuedThreads()：返回所有等待获取许可证的线程集合
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/limit")
    public String limt() throws InterruptedException {
        if(!semaphore.tryAcquire()){
            return "请求超限,max sempahore=5";
        }else{
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                log.error("error",e);
            }finally {
                semaphore.release();
            }
        }
        return "请求成功";
    }

    /**
     * 限流，拿不到凭证的阻塞
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/limit2")
    public String limt2() throws InterruptedException {
        try {
            semaphore.acquire(1);
            log.info("thread:{},拿到凭证",Thread.currentThread().getName());
            Thread.sleep(1000);
        }catch (Exception e){
            log.error("error",e);
        }finally {
            log.info("thread:{},释放凭证",Thread.currentThread().getName());
            semaphore.release(1);
        }

        return "请求成功";
    }

    /**
     * redis限流，拿不到凭证的阻塞
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/limit3")
    public String limt3() throws InterruptedException {
        RSemaphore rSemaphore = redissonClient.getSemaphore("limitTest");
        try {
            rSemaphore.acquire(1);
            log.info("thread:{},拿到凭证",Thread.currentThread().getName());
            Thread.sleep(1000);
        }catch (Exception e){
            log.error("error",e);
        }finally {
            log.info("thread:{},释放凭证",Thread.currentThread().getName());
            rSemaphore.release(1);
        }

        return "请求成功";
    }

//    public static void main(String[] args) {
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://10.1.62.165:6379").setPassword("Redis@456_$%^");
//        Boolean bool = Redisson.create(config).getSemaphore("limitTest").trySetPermits(5);
//    }
}
