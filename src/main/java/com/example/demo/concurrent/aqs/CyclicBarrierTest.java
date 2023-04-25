package com.example.demo.concurrent.aqs;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierTest {
    private static ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);
    public static void main(String[] args) {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        cyclicBarrierTest.count();
    }

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
        int result = 0;
        for (String s: map.keySet()) {
            result = result+map.get(s);
        }
        System.out.println("三人的平均成绩="+result/3);
    });

    private void count(){
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                int score = ThreadLocalRandom.current().nextInt(2) * 30 + 60;
                map.put(Thread.currentThread().getName(), score);
                System.out.println(Thread.currentThread().getName()
                        + "同学的平均成绩为：" + score);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

class CyclicBarrierTest2 {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 1000, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                (r) -> new Thread(r, counter.addAndGet(1) + " 号 "),
                new ThreadPoolExecutor.AbortPolicy());
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,
                () -> System.out.println("裁判：比赛开始~~"));

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.submit(new Runner(cyclicBarrier));
        }

    }

    static class Runner extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Runner(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                int sleepMills = ThreadLocalRandom.current().nextInt(1000);
                Thread.sleep(sleepMills);
                System.out.println(Thread.currentThread().getName() + " 选手已就位, 准备共用时： " + sleepMills + "ms" + cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
