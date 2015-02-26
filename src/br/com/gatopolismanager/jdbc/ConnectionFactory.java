package br.com.gatopolismanager.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public Connection getConnection () {
		try {  
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (Exception e) {  
            System.out.println("ERRO");  
            e.printStackTrace();  
        }  
		
		try {
			return DriverManager.getConnection("jdbc:mysql://br-cdbr-azure-south-a.cloudapp.net:3306/gatopolisphpdb", "b33f81945fc541", "a2536739");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
