package br.com.escolaarcadia.DBenchmarker.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Usuario on 05/04/2015.
 */
public interface TargetDAOListener {
    public void beforeOpenConnection() throws InterruptedException;

    public void beforeOpenPreparedStatement(Connection connection) throws InterruptedException;

    public void beforeExecuteSql(PreparedStatement preparedStatement) throws InterruptedException;

    public void afterExecuteSql(PreparedStatement preparedStatement) throws InterruptedException;
    
    public void afterClosePreparedStatement() throws InterruptedException;
    
    public void afterCloseConnection() throws InterruptedException;
}
