package br.com.farofa.gm.manager;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataBaseManager {
	private static String enviroment;
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;

	public static EntityManager getEntityManager() {
		if(manager == null || !manager.isOpen()){
			factory = Persistence.createEntityManagerFactory(getEnviroment());
			manager = factory.createEntityManager();
		}
		return manager;
	}
	
	public static EntityManagerFactory getFactory() {
		if(factory == null || !factory.isOpen()){
			factory = Persistence.createEntityManagerFactory(getEnviroment());
		}
		return factory;
	}
	
	public static void close(){
		manager.close();
		factory.close();
	}
	
	public static String getEnviroment(){
		if (enviroment != null && !enviroment.equals(""))
			return enviroment;
		
		enviroment = Enviroment.banco_teste.name();
		
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			String hostname = addr.getHostName();
			//Esse é o nome da máquina printada na tela, quando rodado no azure
			if (hostname.equals("RD00155D003742")) {
				enviroment = Enviroment.gatopolis_v2_db.name();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return enviroment;
	}
}
