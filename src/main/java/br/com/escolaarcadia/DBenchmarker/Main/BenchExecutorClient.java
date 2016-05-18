package br.com.escolaarcadia.DBenchmarker.Main;

import br.com.escolaarcadia.DBenchmarker.Model.DAOFactory;
import br.com.escolaarcadia.DBenchmarker.Model.LogDAO;
import br.com.escolaarcadia.DBenchmarker.Model.TargetDAO;

public class BenchExecutorClient implements Runnable {

	private BenchExecutor benchExecutor;
	private int executions;
	private long id;

	public BenchExecutorClient(BenchExecutor benchExecutor) {
		this.benchExecutor = benchExecutor;
		executions = 0;
	}
	
	@Override
	public void run() {
		//record the id of this thread
		id = Thread.currentThread().getId();
		
		LogDAO logDAO = DAOFactory.newLogDAOFactory().getLogDao();		
		TargetDAO targetDAO = DAOFactory.newTargetDAOFactory().getTargetDao();
		
		targetDAO.setTargetDAOListener(new TargetDAOListenerImpl(logDAO));
		
		while(!benchExecutor.isTerminated()) {
			try {
				targetDAO.executeSql(); 
			} catch (InterruptedException e) {
				break;
			}
			executions++;
		}
		
	}

	public int getExecutions() {
		return executions;
	}
	
	public long getId() {
		return id;
	}
	
}
