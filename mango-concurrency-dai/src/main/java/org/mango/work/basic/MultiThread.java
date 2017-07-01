package org.mango.work.basic;

/**
 * Created by 戴礼明 on 2017/6/2.
 * 多个线程多个锁的情况   对象锁以及类级别锁的区别
 */
public class MultiThread  extends Thread{

	//private   int number = 0;
	private static   int number = 0;

	private static synchronized void printNum(boolean isA){
		try {

		if(isA){
			 number = 100;
			 System.out.println("the tag is A,the number set over!");
			Thread.sleep(1000);
 		 }else{
			 number = 200;
			 System.out.println("the tag is B,the number set over!");
		 }
			System.out.println(number);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

   public  static  void main(String[] args){
         final MultiThread multiThreadA = new MultiThread();
         final  MultiThread multiThreadB = new MultiThread();
         Thread threadA = new Thread(new Runnable() {
			 public void run() {
				   multiThreadA.printNum(true);
			 }
		 });

	   Thread threadB = new Thread(new Runnable() {
		   public void run() {
			   multiThreadB.printNum(false);
		   }
	   });

	   threadA.start();
	   threadB.start();
   }


}
