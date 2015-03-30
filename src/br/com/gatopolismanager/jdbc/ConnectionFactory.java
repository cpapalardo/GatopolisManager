package br.com.gatopolismanager.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import com.microsoft.sqlserver.jdbc.*;

public class ConnectionFactory {
	public Connection getConnection () {
		String connectionString = "jdbc:sqlserver://daci0gzkgl.database.windows.net:1433" + ";" +  
		        "database=gatopolis_db" + ";" + 
		        "user=GPAdmin@daci0gzkgl.database.windows.net" + ";" +  
		        "password=GPserver2015";
		
		try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {  
            System.out.println("Driver not found!");
            e.printStackTrace();  
        }  
		
		try {
			return DriverManager.getConnection(connectionString);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
