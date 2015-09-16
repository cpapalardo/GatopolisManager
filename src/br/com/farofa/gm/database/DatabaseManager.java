package br.com.farofa.gm.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseManager {
	private static EntityManagerFactory factory;
	
	public static EntityManagerFactory getFactory () {
		if (factory == null || !factory.isOpen()) 
			factory = Persistence.createEntityManagerFactory("gatopolis_db");
		return factory;
	}
	
	public static void closeFactory () {
		factory = null;
	}
	
	public static EntityManager getEntityManager () {
		return getFactory().createEntityManager();
	}
	
}
