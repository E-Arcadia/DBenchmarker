package br.com.escolaarcadia.DBenchmarker.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.escolaarcadia.DBenchmarker.Model.LogDAO;
import br.com.escolaarcadia.DBenchmarker.Model.LogEntity;
import br.com.escolaarcadia.DBenchmarker.Model.TargetDAOListener;
import br.com.escolaarcadia.DBenchmarker.util.Log;

public class TargetDAOListenerImpl implements TargetDAOListener {
    	
    	LogEntity logEntity;
    	Long executionTimeConnection;
    	Long executionTimeStatement;
    	Long executionTimeSQL;
    	LogDAO logDAO;
    	
    	public TargetDAOListenerImpl(LogDAO logDAO) {
    		this.logDAO = logDAO;
    	}
    	
        @Override
        public void beforeOpenConnection() throws InterruptedException {
        	//executionTimeConnection = System.currentTimeMillis();
        	executionTimeConnection = System.nanoTime();
        }

        @Override
        public void beforeOpenPreparedStatement(Connection connection) throws InterruptedException {
        	//executionTimeStatement = System.currentTimeMillis();
        	executionTimeStatement = System.nanoTime();
        }

        @Override
        public void beforeExecuteSql(PreparedStatement preparedStatement) throws InterruptedException {
        	//executionTimeSQL = System.currentTimeMillis();
        	executionTimeSQL = System.nanoTime();
        }

        @Override
        public void afterExecuteSql(PreparedStatement preparedStatement) throws InterruptedException {
        	
        	//Long currentTime = System.currentTimeMillis();
        	Long currentTime = System.nanoTime();
        	
        	Long insertedId = null;
        	
        	try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    insertedId = generatedKeys.getLong(1);
            } catch (SQLException e) {
            	Log.err("Error occured while retreiving inserted id");
            }
        	
        	LogEntity logEntity = new LogEntity();
        	logEntity.setDataId(insertedId);
        	logEntity.setThreadId(Thread.currentThread().getId());
        	logEntity.setDate(Calendar.getInstance());
        	logEntity.setSize(0L);
        	logEntity.setExecutionTimeConnection(currentTime - executionTimeConnection);
        	logEntity.setExecutionTimeStatement(currentTime - executionTimeStatement);
        	logEntity.setExecutionTimeSQL(currentTime - executionTimeSQL);
        	
        	logDAO.insert(logEntity);
        }
        
        @Override
        public void afterClosePreparedStatement() throws InterruptedException {}
        
        @Override
        public void afterCloseConnection() throws InterruptedException {}
}
