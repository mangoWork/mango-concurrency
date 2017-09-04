package org.mango.work.designpattern.prividercustomer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Privider  implements Runnable{

    private BlockingQueue queue;
    private static AtomicInteger count = new AtomicInteger();
    private   volatile  boolean  isStop = false;


    public Privider(BlockingQueue queue) {
        this.queue = queue;
        Thread.currentThread().setName("privider==============>>");
    }

    @Override
    public void run() {
        while (!isStop){
            int id = count.incrementAndGet();
            Data data = new Data(id, "数据："+id);
            System.out.println("当前线程是"+Thread.currentThread().getName()+"         封装了数据：   "+data.getName());
            try {
                boolean success = queue.offer(data, 10, TimeUnit.MILLISECONDS);
                if(!success){
                    System.out.println("提交数据到队列中失败！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        isStop = true;
    }

}
