package org.mango.work.basic;



/**
 * 单列模式
 */
public class SingleTon {

    private static class Instance{

        private static Instance single = new Instance();
    }


    public static Instance getInstance(){

         return Instance.single;
    }
}



