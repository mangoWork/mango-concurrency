package org.mango.work.designpattern.masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Work implements Runnable{


    private ConcurrentLinkedDeque<Task> workQueue;
   private  ConcurrentHashMap<String,Object> resultMap;

    @Override
    public void run() {
        while (true){
            Task input = this.workQueue.poll();
            if (input == null){
                break;
            }
            //处理任务
            Object result = handle(input);
            this.resultMap.put(Integer.toString(input.getId()), result);
        }
    }

    private Object handle(Task task) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return task.getPrice();
    }


    public void setWorkQueue(ConcurrentLinkedDeque<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap<String,Object> resultMap) {
        this.resultMap = resultMap;
    }
}
