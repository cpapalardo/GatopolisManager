package br.com.farofa.gm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private boolean test = true;
	
	private static String AZURE_CONECTION_STRING = "jdbc:sqlserver://daci0gzkgl.database.windows.net:1433" + ";" +  
	        "database=gatopolis_db" + ";" + 
	        "user=GPAdmin@daci0gzkgl.database.windows.net" + ";" +  
	        "password=GPserver2015";
	
	private static String VM_CONECTION_STRING = "jdbc:sqlserver://192.168.0.180:1433" + ";" +
	        "database=banco_teste" + ";" + 
	        "user=admin" + ";" +  
	        "password=admin";
	
	public Connection getConnection () {
		try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {  
            System.out.println("Driver not found!");
            e.printStackTrace();  
        }  
		
		try {
			if (test) {
				return DriverManager.getConnection(VM_CONECTION_STRING);
			}else{
				return DriverManager.getConnection(AZURE_CONECTION_STRING);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
