package br.com.escolaarcadia.DBenchmarker.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Created by Danilo Souza Morães on 05/04/2015.
 */
public class Config {

	private static final int DEFAULT_THREADS = 1;
	private static final int DEFAULT_DELAY = 1000;
    private static Properties properties = new Properties();

    public static void load(Path path) {
        try {
            properties.load(new InputStreamReader(new FileInputStream(path.toFile()), "UTF-8"));
        } catch (IOException e) {
            Log.err(path.getFileName() + " not found");
        }
    }

    public static String getTargetJDBCConnector() {
        return properties.getProperty("dbenchmarker.target.jdbc.Connector");
    }

    public static String getTargetJDBCDriver() {
        return properties.getProperty("dbenchmarker.target.jdbc.Driver");
    }

    public static String getTargetJDBCURL() {
        return properties.getProperty("dbenchmarker.target.jdbc.URL");
    }

    public static String getTargetDBUser() {
        return properties.getProperty("dbenchmarker.target.db.user");
    }

    public static String getTargetDBPassword() {
        return properties.getProperty("dbenchmarker.target.db.password");
    }

    public static String getTargetDBSQL() {
        return properties.getProperty("dbenchmarker.target.db.sql");
    }

    //Not mandatory
    public static ConnectionType getTargetDBConnectionType() {
        if(properties.getProperty("dbenchmarker.target.db.connection_type", "").toLowerCase().equalsIgnoreCase("pool"))
            return ConnectionType.POOL;
        return ConnectionType.STANDARD;
    }

    public enum ConnectionType {STANDARD, POOL}
    
    public static String getLogMysqlJDBCURL() {
        return properties.getProperty("dbenchmarker.log.jdbc.URL");
    }
    
    public static String getLogDBUser() {
        return properties.getProperty("dbenchmarker.log.db.user");
    }

    public static String getLogDBPassword() {
        return properties.getProperty("dbenchmarker.log.db.password");
    }
    
    public static String getLogDBSQL() {
        return properties.getProperty("dbenchmarker.log.db.sql");
    }
    
    public static int getExecutorThreads() {
    	String threads = properties.getProperty("dbenchmarker.executor.threads");
    	int threadsInt = DEFAULT_THREADS;
    	if(threads != null)
    		threadsInt = Integer.parseInt(threads);
    	
    	return threadsInt;
    }
    
    public static long getAnalyzerDelay() {
    	String delay = properties.getProperty("dbenchmarker.analyzer.delay");
    	int delayLong = DEFAULT_DELAY;
    	if(delay != null)
    		delayLong = Integer.parseInt(delay);
    	
    	return delayLong;
    }
    
}
