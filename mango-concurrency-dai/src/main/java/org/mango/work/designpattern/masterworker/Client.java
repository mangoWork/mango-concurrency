package org.mango.work.designpattern.masterworker;

import java.util.Map;
import java.util.Random;

public class Client {


    public static void main(String[] args){
        Work work = new Work();
        Master master = new Master(work, 10);
        Random random = new Random();
        for (int i=0;i<10;i++){
            Task task = new Task(i, "task"+i, random.nextLong());
            master.submmit(task);
        }

        master.executeWork();
        long startTime = System.currentTimeMillis();
        while (true){
            if(master.isComplete()){
                long runTime = System.currentTimeMillis()-startTime;
                Object ret = master.getResut();
                System.out.println("结果："+ret+"  运行时间： "+runTime);
                break;
            }
        }
    }

}
