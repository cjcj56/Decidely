package kisch.binyamin.decidely.utils.db;

import java.sql.Connection;
import java.util.Properties;

public interface DbUtils {

	Connection getConnection();
	String getDriverName();
	String getConnectionString();
	Properties getProperties();
	
}
