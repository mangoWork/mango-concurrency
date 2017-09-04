package org.mango.work.designpattern.masterworker;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 用于分配任务
 * 收集结果集
 */
public class Master {

   //1. 装配任务的集合
    private ConcurrentLinkedDeque<Task> workQueue = new ConcurrentLinkedDeque<Task>();
    //2. 装配work
    private HashMap<String , Thread> works = new HashMap<>();
    //3. 装配结果集
    private ConcurrentHashMap<String, Object> resultsMap = new ConcurrentHashMap<>();

    //4. 构造方法
    public Master(Work work, int countWork) {
        work.setWorkQueue(this.workQueue);
        work.setResultMap(this.resultsMap);
        for (int i=0; i<countWork; i++){
            this.works.put("子节点"+Integer.valueOf(i), new Thread(work));
        }
    }

    //5. 提交任务
    public void submmit(Task task){
         this.workQueue.add(task);
    }

    //6. 执行
    public void executeWork(){
         for(Map.Entry<String , Thread> me :this.works.entrySet()){
             me.getValue().start();
        }
    }

    public Object getResut(){
        long result = 0;
        for (Map.Entry<String, Object> me:resultsMap.entrySet()){
             result += (Long)me.getValue();
        }
        return result;
    }

    public boolean isComplete() {
        for (Map.Entry<String, Thread> me:works.entrySet()){
              if (me.getValue().getState() != Thread.State.TERMINATED){
                  return false;
              }
        }
        return true;
    }
}
