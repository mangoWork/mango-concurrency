package org.mango.work.designpattern.prividercustomer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable{

    private volatile boolean isStop = false;

    private  BlockingQueue<Data> queue;

    private static Random random = new Random();
    private static AtomicInteger count = new AtomicInteger();

    public Customer(BlockingQueue queue) {
        this.queue = queue;
        Thread.currentThread().setName("customer==============>>");
    }

    @Override
    public void run() {
         while (true){
             try {
                 Thread.sleep(random.nextInt(100));
                 Data data = this.queue.take();
                 System.out.println("当前线程是"+Thread.currentThread().getName()+"         获取了数据：   "+data.getName());
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

         }
    }

}
