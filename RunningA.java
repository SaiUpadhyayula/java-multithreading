package com.java.concurrency;

public class RunningA {
	
	public static void main(String[] args) throws InterruptedException{
		
		A a = new A();
		
		Runnable runnable1 = () -> a.a();
		Runnable runnable2 = () -> a.b();
		
		Thread t = new Thread(runnable1);
		t.start();
		
		Thread t2 = new Thread(runnable2);
		t2.start();
		
		t.join();
		t2.join();
	}
}
