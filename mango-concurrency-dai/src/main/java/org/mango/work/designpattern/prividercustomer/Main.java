package org.mango.work.designpattern.prividercustomer;

import org.mango.work.threadpool.CachedPool;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        BlockingDeque queue = new LinkedBlockingDeque(10);

        Privider p1 = new Privider(queue);
        Privider p2 = new Privider(queue);
        Customer c1 = new Customer(queue);
        Customer c2 = new Customer(queue);
        Customer c3 = new Customer(queue);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(p1);
        executorService.execute(p2);
        executorService.execute(c1);
        executorService.execute(c2);
        executorService.execute(c3);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        p1.stop();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p2.stop();

    }
}
