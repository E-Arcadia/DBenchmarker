package br.com.escolaarcadia.DBenchmarker.Model;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class DriverShim extends Driver {
	
	private Driver driver;
	
	public DriverShim() throws SQLException {
		super();
	}

	DriverShim(String connectorPath, String driverClassString) throws SQLException {
		try {
			URL[] urls = {new File(connectorPath).toURI().toURL()};
	        URLClassLoader classLoader = new URLClassLoader(urls);
	        //Class<?> driverClass = jarClassLoader.loadClass(driverClassString);
	        driver = (Driver) Class.forName(driverClassString, true, classLoader).newInstance();
		} catch (ClassNotFoundException e) {
	        throw new DAOException("JDBC Driver not found", e);
	    } catch (MalformedURLException e) {
	        throw new DAOException("URL to the JDBC Driver is not reachable", e);
	    } catch (InstantiationException | IllegalAccessException e) {
	        throw new DAOException("Error when trying to instantiate JDBC Driver", e);
	    }
	}
	@Override
	public boolean acceptsURL(String u) throws SQLException {
		return this.driver.acceptsURL(u);
	}
	@Override
	public Connection connect(String u, Properties p) throws SQLException {
		return this.driver.connect(u, p);
	}
	@Override
	public int getMajorVersion() {
		return this.driver.getMajorVersion();
	}
	@Override
	public int getMinorVersion() {
		return this.driver.getMinorVersion();
	}
	@Override
	public DriverPropertyInfo[] getPropertyInfo(String u, Properties p) throws SQLException {
		return this.driver.getPropertyInfo(u, p);
	}
	@Override
	public boolean jdbcCompliant() {
		return this.driver.jdbcCompliant();
	}

}
