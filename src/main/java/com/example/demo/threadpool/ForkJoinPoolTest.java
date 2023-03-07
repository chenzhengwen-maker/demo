package com.example.demo.threadpool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8};
        System.out.println(Arrays.stream(a).sum());
        Long invoke = new ForkJoinPool().invoke(new LongSum(a, 0, a.length));
        System.out.println(invoke);
    }
}

class LongSum extends RecursiveTask<Long>{

    // 任务拆分最小阈值
    static final int SEQUENTIAL_THRESHOLD = 10000;

    int low;
    int high;
    int[] array;

    LongSum(int[] arr, int lo, int hi) {
        array = arr;
        low = lo;
        high = hi;
    }

    @Override
    protected Long compute() {
        //当任务拆分到小于等于阀值时开始求和
        if (high - low <= SEQUENTIAL_THRESHOLD) {

            long sum = 0;
            for (int i = low; i < high; ++i) {
                sum += array[i];
            }
            return sum;
        } else {  // 任务过大继续拆分
            int mid = low + (high - low) / 2;
            LongSum left = new LongSum(array, low, mid);
            LongSum right = new LongSum(array, mid, high);
            // 提交任务
            left.fork();
            right.fork();
            //获取任务的执行结果,将阻塞当前线程直到对应的子任务完成运行并返回结果
            long rightAns = right.join();
            long leftAns = left.join();
            return leftAns + rightAns;
        }
    }
}
