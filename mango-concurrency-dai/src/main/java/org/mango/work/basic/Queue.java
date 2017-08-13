package org.mango.work.basic;


/*
 *模拟队列
 */

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue {

    //队列最小的容量
    private final int MIX_SIZE;
    //队列最大容量
    private final int MAX_SIZE;

    private LinkedList<Object> lists = new LinkedList<>();

    private final Object lock = new Object();

    private   AtomicInteger count = new AtomicInteger();

    public Queue(int maxSize){
         this.MIX_SIZE = 0;
         this.MAX_SIZE = maxSize;
    }

    public Queue(int mixSize, int maxSize){
        this.MIX_SIZE = mixSize;
        this.MAX_SIZE = maxSize;
    }

    /**
     * 向队列中添加元素
     * @param object
     */
    public void put(Object object){
        synchronized(lock){
            while (count.get() == MAX_SIZE){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lists.add(object);
            count.incrementAndGet();
            System.out.println("添加了新的元素："+object);
            lock.notify();
        }
    }

    /**
     * 向队列中获取元素
     * @return
     */
    public Object take(){
        Object object = null;
        synchronized(lock){
           while (count.get() == MIX_SIZE){
               try {
                   lock.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           object = lists.removeFirst();
           count.decrementAndGet();
             lock.notify();
        }
        return object;
    }


    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue(5);
        queue.put("1");
        queue.put("2");
        queue.put("3");
        queue.put("4");
        queue.put("5");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
               queue.put("6");
               queue.put("7");

            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Object o1 = queue.take();
                System.out.println("移除的元素是："+o1);
                Object o2 = queue.take();
                System.out.println("移除的元素是："+o2);
            }
        }, "t2");

        t1.start();
        TimeUnit.SECONDS.sleep(3);
        t2.start();
    }

}
