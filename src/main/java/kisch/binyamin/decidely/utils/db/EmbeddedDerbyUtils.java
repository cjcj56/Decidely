package kisch.binyamin.decidely.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Service;

//@Service
public class EmbeddedDerbyUtils implements DbUtils {

	private final String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
	private final String connectionString = "jdbc:derby:derbyDB;create=true";
	private Connection connection;
	private Properties properties = new Properties();

	public EmbeddedDerbyUtils() {
		try {
			Class.forName(getDriverName()).newInstance();
			connection = DriverManager.getConnection(getConnectionString(), getProperties());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public String getDriverName() {
		return this.driverName;
	}

	@Override
	public String getConnectionString() {
		return connectionString;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

}
