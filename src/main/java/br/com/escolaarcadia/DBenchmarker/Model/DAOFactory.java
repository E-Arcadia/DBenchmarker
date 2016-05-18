package br.com.escolaarcadia.DBenchmarker.Model;

import java.sql.Connection;

import br.com.escolaarcadia.DBenchmarker.util.Config;

/**
 * Created by Danilo Souza Mor�es on 05/04/2015.
 */
public abstract class DAOFactory {
    
    public static DAOFactory newLogDAOFactory() {
    	return new LogDAOFactory();
    }
    
    public static DAOFactory newTargetDAOFactory() {
    	DAOFactory instance = null;

        if(!Config.getTargetDBConnectionType().equals(Config.ConnectionType.POOL))
            instance = new DriverDAOFactory();

        //Need to implement the pooled connection

        return instance;
    }

    public abstract Connection getConnection() throws DAOException;

    public TargetDAO getTargetDao() {
        return new TargetDAO(this);
    }
    
    public LogDAO getLogDao() {
        return new LogDAO(this);
    }
}
