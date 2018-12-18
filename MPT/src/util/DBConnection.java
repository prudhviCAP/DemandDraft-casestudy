package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException
	{
		
		FileInputStream fis = new FileInputStream("resources/DB.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		String u= prop.getProperty("URL");
		String user = prop.getProperty("username");
		String pass=prop.getProperty("password");
		String driver = prop.getProperty("driver");
		Class.forName(driver);
		Connection c = DriverManager.getConnection(u,user,pass);
		return c;
	}

}
