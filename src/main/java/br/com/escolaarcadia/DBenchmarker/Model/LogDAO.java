package br.com.escolaarcadia.DBenchmarker.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.escolaarcadia.DBenchmarker.util.Config;

/**
 * Created by Usuario on 05/04/2015.
 */
public class LogDAO {
    DAOFactory daoFactory;
    Connection connection;

    public LogDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        connection = daoFactory.getConnection();
    }

    public void insert(LogEntity logEntity) {

    	PreparedStatement preparedStatement = null;
        try {
            
            String sql = Config.getLogDBSQL();
            if(sql == null)
            	sql = "";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, logEntity.getDataId());
            preparedStatement.setLong(2, logEntity.getThreadId());
            preparedStatement.setLong(3, 0);
            preparedStatement.setLong(4, logEntity.getExecutionTimeStatement());
            preparedStatement.setLong(5, logEntity.getExecutionTimeSQL());
            preparedStatement.setTimestamp(6, new Timestamp(logEntity.getDate().getTimeInMillis()));
            preparedStatement.setLong(7, logEntity.getSize());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if(preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DAOException("Error trying to close PreparedStatement", e);
                }
/*            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DAOException("Error trying to close Connection", e);
                }*/
        }
    }
}