package com.java.concurrency;

public class ProducerConsumer {

	private static Object lock = new Object();	
	private static int counter;
	private static int[] buffer;
	
	public static class Producer{
		public void produce() throws InterruptedException{
			synchronized(lock){
				if(isFull(buffer)){
					lock.wait();
				}			
				buffer[counter++] = 1;
				lock.notify();
			}
		}
	}
	
	public static class Consumer{
		public void consume() throws InterruptedException{
			synchronized(lock){
				if(isEmpty(buffer)){
					lock.wait();
				}
				buffer[--counter] = 0;
				lock.notify();
			}
		}
	}
	
	public static boolean isFull(int[] buffer){
		return counter == buffer.length;
	}
	
	public  static boolean isEmpty(int[] buffer){
		return counter == 0;
	}
	
	public static void main(String[] args) throws InterruptedException{
		buffer = new int[10];
		counter = 0;
		
		Producer producer = new Producer();
		Consumer consumer = new Consumer();
		
		Runnable producerRunnable = () -> {
			for(int i = 0; i < 100; i++){
				try {
					producer.produce();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("Done Producing!");
		};
		
		Runnable consumerRunnable = () -> {
			for(int i = 0; i < 100; i++){
				try {
					consumer.consume();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("Done Consuming!");
		};
		
		Thread producerThread = new Thread(producerRunnable);
		Thread consumerThread = new Thread(consumerRunnable);
		
		producerThread.start();
		consumerThread.start();
		
		producerThread.join();
		consumerThread.join();
		
		System.out.println("Data value is "+counter);
	}
}
