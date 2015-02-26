package br.com.motogatomanager.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public Connection getConnection () {
		String connectionString = "jdbc:sqlserver://daci0gzkgl.database.windows.net:1433;"
				+ "database=gatopolis_db;"
				+ "user=GPAdmin@daci0gzkgl;"
				+ "password={GPserver2015};"
				+ "encrypt=true;"
				+ "hostNameInCertificate=*.database.windows.net;"
				+ "loginTimeout=30;";
		
		// The types for the following variables are
		// defined in the java.sql library.
		Connection connection = null;  // For making the connection

		try
		{
		    // Ensure the SQL Server driver class is available.
		    //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		    // Establish the connection.
		    connection = DriverManager.getConnection(connectionString);

		} catch (Exception e)
        {
            System.out.println("Exception " + e.getMessage());
            e.printStackTrace();
        }
		return connection;
	}
}