package br.com.farofa.gm.manager;

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
	
	public static void close(){
		manager.close();
		factory.close();
	}
	
	public static String getEnviroment(){
		enviroment = Enviroment.banco_teste.name();
		return enviroment;
	}
}
