package br.com.escolaarcadia.DBenchmarker.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BenchExecutor {

	private int threads;
	private ExecutorService executorService;
	private List<BenchExecutorClient> executors;
	private volatile boolean terminated;
	
	public BenchExecutor(int threads) {
		this.threads = threads;
		terminated = false;
		executorService = Executors.newFixedThreadPool(threads);
		executors = Collections.synchronizedList(new ArrayList<BenchExecutorClient>());
	}
	
	public void execute() {
		for(int i = 0; i < threads; i++) {
			/*
			 * Creates the client that will execute the sql. Passes a reference of BenchExecutor
			 * so that the client can know when BenchExecutor has been terminated.
			 */
			BenchExecutorClient executor = new BenchExecutorClient(this);
			executors.add(executor);
			executorService.execute(executor);
		}
		
	}
	
	public void terminate() {
		executorService.shutdown();
		terminated = true;
		try {
			executorService.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {}
	}
	
	public boolean isTerminated() {
		return terminated;
	}

	public List<BenchExecutorClient> getExecutors() {
		return executors;
	}

}
