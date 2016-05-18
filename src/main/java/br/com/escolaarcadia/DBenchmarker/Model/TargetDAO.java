package br.com.escolaarcadia.DBenchmarker.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.escolaarcadia.DBenchmarker.util.Config;

/**
 * Created by Usuario on 05/04/2015.
 */
public class TargetDAO {
    DAOFactory daoFactory;
    TargetDAOListener targetDAOListener;
    Connection connection;

    public TargetDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        connection = daoFactory.getConnection();
    }

    public void executeSql() throws InterruptedException {

        PreparedStatement preparedStatement = null;
        try {
        	
            targetDAOListener.beforeOpenConnection();

            targetDAOListener.beforeOpenPreparedStatement(connection);

            preparedStatement = connection.prepareStatement(Config.getTargetDBSQL(), Statement.RETURN_GENERATED_KEYS);

            targetDAOListener.beforeExecuteSql(preparedStatement);

            preparedStatement.execute();

            targetDAOListener.afterExecuteSql(preparedStatement);
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {

            if(preparedStatement != null)
                try {
                    preparedStatement.close();
                    targetDAOListener.afterClosePreparedStatement();
                } catch (SQLException e) {
                    throw new DAOException("Error trying to close PreparedStatement", e);
                }

 /*           if(connection != null)
                try {
                    connection.close();
                    targetDAOListener.afterCloseConnection();
                } catch (SQLException e) {
                    throw new DAOException("Error trying to close Connection", e);
                }*/
        }
    }

    public void setTargetDAOListener(TargetDAOListener targetDAOListener) {
        this.targetDAOListener = targetDAOListener;
    }
}
