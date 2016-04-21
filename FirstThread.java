package com.java.concurrency;

public class FirstThread {
	public static void main(String[] args){
		Runnable runnable = () -> {
			System.out.println("I am running in "+ Thread.currentThread().getName());
		};
		
		Thread t = new Thread(runnable);
		t.setName("My Thread");
		t.start();
//		t.run(); -> Used to run the main thread
	}
}
