package br.com.escolaarcadia.DBenchmarker.Model;

import java.util.Calendar;

public class LogEntity {
	//if the target sql was an insert, id of the inserted row
	private Long dataId;
	//id of the thread that inserted the row
	private Long threadId;
	//time spent executing the sql before connection
	private Long executionTimeConnection;
	//time spent executing the sql before statement
	private Long executionTimeStatement;
	//time spent executing the sql
	private Long executionTimeSQL;
	//date when it was executed
	private Calendar date;
	//size of the data inserted
	private Long size;

	
	public LogEntity() {}

	public LogEntity(Long dataId, Long threadId, Long executionTimeConnection,
			Long executionTimeStatement, Long executionTimeSQL, Calendar date,
			Long size) {
		this.dataId = dataId;
		this.threadId = threadId;
		this.executionTimeConnection = executionTimeConnection;
		this.executionTimeStatement = executionTimeStatement;
		this.executionTimeSQL = executionTimeSQL;
		this.date = date;
		this.size = size;
	}


	public Long getDataId() {
		return dataId;
	}


	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}


	public Long getThreadId() {
		return threadId;
	}


	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}


	public Long getExecutionTimeConnection() {
		return executionTimeConnection;
	}


	public void setExecutionTimeConnection(Long executionTimeConnection) {
		this.executionTimeConnection = executionTimeConnection;
	}


	public Long getExecutionTimeStatement() {
		return executionTimeStatement;
	}


	public void setExecutionTimeStatement(Long executionTimeStatement) {
		this.executionTimeStatement = executionTimeStatement;
	}


	public Long getExecutionTimeSQL() {
		return executionTimeSQL;
	}


	public void setExecutionTimeSQL(Long executionTimeSQL) {
		this.executionTimeSQL = executionTimeSQL;
	}


	public Calendar getDate() {
		return date;
	}


	public void setDate(Calendar date) {
		this.date = date;
	}


	public Long getSize() {
		return size;
	}


	public void setSize(Long size) {
		this.size = size;
	}

}
