package com.java.concurrency;

public class A {
	
	private Object key1 = new Object();
	private Object key2 = new Object();
	
	public void a(){
		synchronized(key1){
			System.out.println("I am running in Thread "+Thread.currentThread().getName()+" in a().");
			b();
		}		
	}

	public void b() {
		synchronized(key2){
			System.out.println("I am running in Thread "+Thread.currentThread().getName()+" in b().");
			c();
		}
		
	}

	public void c() {
		synchronized(key1){
			System.out.println("I am running in Thread "+Thread.currentThread().getName()+" in c().");
		}		
	}
}
