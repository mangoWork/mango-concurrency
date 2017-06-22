package org.mango.work.basic;

/**
 * Created by 戴礼明 on 2017/6/2.
 */
public class ThreadInfo extends Thread{

	private int number = 10;

	@Override
	public synchronized  void run() {
		System.out.println("thred name:"+this.currentThread().getName()  +"          number:  "+number--);
	}


public  static  void main(String[] args){
	ThreadInfo thread = new ThreadInfo();
	Thread thread1 = new Thread(thread,"thread1");
	Thread thread2 = new Thread(thread,"thread2");
	Thread thread3 = new Thread(thread,"thread3");
	Thread thread4 = new Thread(thread,"thread4");
	Thread thread5 = new Thread(thread,"thread5");
	Thread thread6 = new Thread(thread,"thread6");
	Thread thread7 = new Thread(thread,"thread7");
	Thread thread8 = new Thread(thread,"thread8");
	Thread thread9 = new Thread(thread,"thread9");
	Thread thread10 = new Thread(thread,"thread10");
    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
    thread5.start();
	thread6.start();
	thread7.start();
	thread8.start();
	thread9.start();
	thread10.start();
}


}
