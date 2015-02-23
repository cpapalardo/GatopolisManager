package br.com.motogatomanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.motogatomanager.db.ConnectionFactory;
import br.com.motogatomanager.modelo.School;

public class SchoolDAO {
	
	public SchoolDAO () {
		
	}
	
	public void saveSchool (School school) {
		String s = "jdbc:sqlserver://daci0gzkgl.database.windows.net:1433;"
				+ "database=gatopolis_db;"
				+ "user=GPAdmin@daci0gzkgl;"
				+ "password={GPserver2015};"
				+ "encrypt=true;"
				+ "hostNameInCertificate=*.database.windows.net;"
				+ "loginTimeout=30;";
		
		// Connection string for your SQL Database server.
				// Change the values assigned to your_server, 
				// your_user@your_server,
				// and your_password.
				String connectionString = s;

				// The types for the following variables are
				// defined in the java.sql library.
				Connection connection = null;  // For making the connection
				Statement statement = null;    // For the SQL statement
				ResultSet resultSet = null;    // For the result set, if applicable

				try
				{
					com.microsoft.sqlserver.jdbc.SQLServerDriver driver = new com.microsoft.sqlserver.jdbc.SQLServerDriver ();
					
				    // Ensure the SQL Server driver class is available.
				    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

				    // Establish the connection.
				    connection = DriverManager.getConnection(connectionString);

				    // Define the SQL string.
				    String sqlString = 
				        "SET IDENTITY_INSERT Person ON " + 
				            "INSERT INTO Person " + 
				            "(PersonID, LastName, FirstName) " + 
				            "VALUES(1, 'Abercrombie', 'Kim')," + 
				                  "(2, 'Goeschl', 'Gerhard')," + 
				                  "(3, 'Grachev', 'Nikolay')," + 
				                  "(4, 'Yee', 'Tai')," + 
				                  "(5, 'Wilson', 'Jim')";

				    // Use the connection to create the SQL statement.
				    statement = connection.createStatement();

				    // Execute the statement.
				    statement.executeUpdate(sqlString);

				    // Provide a message when processing is complete.
				    System.out.println("Processing complete.");

				}catch (Exception e)
		        {
		            System.out.println("Exception " + e.getMessage());
		            e.printStackTrace();
		        }
		        finally
		        {
		            try
		            {
		                // Close resources.
		                if (null != connection) connection.close();
		                if (null != statement) statement.close();
		                if (null != resultSet) resultSet.close();
		            }
		            catch (SQLException sqlException)
		            {
		                // No additional action if close() statements fail.
		            }
		        }
	}
	
	
}
