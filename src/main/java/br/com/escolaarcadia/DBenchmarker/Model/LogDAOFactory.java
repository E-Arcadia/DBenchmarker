package br.com.escolaarcadia.DBenchmarker.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.com.escolaarcadia.DBenchmarker.util.Config;

/**
 * Created by Usuario on 05/04/2015.
 */
public class LogDAOFactory extends DAOFactory {


    @Override
	public Connection getConnection() throws DAOException {

        Properties props = new Properties();
        props.setProperty("user", Config.getTargetDBUser());
        props.setProperty("password", Config.getTargetDBPassword());

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Config.getTargetJDBCURL(), props);
        } catch (SQLException e) {
            throw new DAOException("Error occured while retrieving a connection", e);
        }
        return connection;
    }
}
