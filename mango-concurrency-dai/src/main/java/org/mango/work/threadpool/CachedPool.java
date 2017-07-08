package org.mango.work.threadpool;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：戴礼明
 * 创建时间： 2017/7/8 0008
 * 联系邮箱：1192297699@qq.com
 */
public class CachedPool {



    public static void main(String[] arg){
        Executor cachedThreadPool = Executors.newCachedThreadPool();
        System.out.println(6);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }

}
