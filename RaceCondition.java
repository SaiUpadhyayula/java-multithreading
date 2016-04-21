package com.java.concurrency;

public class RaceCondition {
	
	public static void main(String[] args) throws InterruptedException{
		LongWrapper longWrapper = new LongWrapper(0L);
		
		Runnable runnable = () -> {
			for(int i = 0; i<1000; i++){
				longWrapper.incrementValue();
			}
		};
		
		Thread[] threads = new Thread[1000];
		for(int i = 0; i < 1000; i++){
			threads[i] = new Thread(runnable);
			threads[i].start();
		}
		
		for(int i = 0; i < 1000; i++){
			threads[i].join();	
		}		
		
		System.out.println("Value = "+longWrapper.getValue());
	}
}