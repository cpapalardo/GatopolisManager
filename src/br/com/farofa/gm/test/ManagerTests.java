package br.com.farofa.gm.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import br.com.farofa.gm.manager.DataBaseManager;

public class ManagerTests {
	public static void main(String[] args) {
		testGetEnviroment();
	}
	
	public static void testGetEnviroment() {
		String hostname = "Unknown";

		try
		{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    hostname = addr.getHostName();
		    
		    System.out.println(hostname);
		}
		catch (UnknownHostException ex)
		{
		    System.out.println("Hostname can not be resolved");
		}
	}
	
	public static void testGetEntityManager() {
		DataBaseManager.getEntityManager();
	}
}
