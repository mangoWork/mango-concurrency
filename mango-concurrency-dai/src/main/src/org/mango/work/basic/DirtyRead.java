package org.mango.work.basic;

/**
 * Created by 戴礼明 on 2017/6/5.
 * 用于模拟脏读
 *如果对应的赋值使用同步   则在获取的时候也许使用同步，否则容易发生脏读
 */
public class DirtyRead {

	private String name = "张三";

	private int age = 10;

	public synchronized void setUserInfo(String userName,int age){
         this.name = userName;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.age = age;
	}

	public  void printUserInfo(){
		System.out.println("用户名是："+name+";年龄是："+age);
	}

	public static void main(String[] args){
            final DirtyRead dirtyReadA = new DirtyRead();
             Thread threadA = new Thread(new Runnable() {
				public void run() {
 					dirtyReadA.setUserInfo("戴礼明",23);
				}
			});
		Thread threadB = new Thread(new Runnable() {
			public void run() {
				dirtyReadA.printUserInfo();
			}
		});
		threadA.start();
		threadB.start();

	}



}
