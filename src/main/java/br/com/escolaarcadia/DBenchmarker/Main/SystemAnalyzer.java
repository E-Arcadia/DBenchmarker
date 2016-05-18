package br.com.escolaarcadia.DBenchmarker.Main;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemAnalyzer {
	
	long delay;
	BenchExecutor benchExecutor;
	ScheduledExecutorService scheduledExecutorService;
	
	public SystemAnalyzer(BenchExecutor benchExecutor, long delay) {
		this.benchExecutor = benchExecutor;
		this.delay = delay;
		this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
	}
	
	public void start() {
		scheduledExecutorService.scheduleWithFixedDelay(new Analyzer(), delay, delay, TimeUnit.MILLISECONDS);
	}
	
	private class Analyzer implements Runnable {
		
		private long counter = 0;
		private long previousTotal = 0;
		@Override
		public void run() {
			List<BenchExecutorClient> executors = benchExecutor.getExecutors();
			System.out.println("---------------------- Iteracao: " + ++counter + " ----------------------");
			int total = 0;
			synchronized(executors) {
				for(BenchExecutorClient executor : executors) {
					int executions = executor.getExecutions();
					System.out.println("Thread " + executor.getId() + ": " + executions);
					total += executions;
				}
				System.out.println("Total Local: " + (total - previousTotal));
				System.out.println("Total: " + total);
				previousTotal = total;
			}
		}
		
	}
	
	public void terminate() {
		scheduledExecutorService.shutdown();
		try {
			scheduledExecutorService.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {}
	}
}
