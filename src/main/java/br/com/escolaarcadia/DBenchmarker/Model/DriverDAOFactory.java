package br.com.escolaarcadia.DBenchmarker.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.com.escolaarcadia.DBenchmarker.util.Config;

/**
 * Created by Usuario on 05/04/2015.
 */
public class DriverDAOFactory extends DAOFactory {

    @Override
	public Connection getConnection() throws DAOException {

        Properties props = new Properties();
        props.setProperty("user", Config.getTargetDBUser());
        props.setProperty("password", Config.getTargetDBPassword());

        String connectorPath = Config.getTargetJDBCConnector();
        
        Connection connection = null;
        try {
        	//If there is a connector path in the conf.properties, load and use it to connect to the database
        	if(connectorPath != null)
        		DriverManager.registerDriver(new DriverShim(connectorPath, Config.getTargetJDBCDriver()));
            
        	connection = DriverManager.getConnection(Config.getTargetJDBCURL(), props);
        } catch (SQLException e) {
            throw new DAOException("Error occured while retrieving a connection", e);
        }
        return connection;
    }
}
