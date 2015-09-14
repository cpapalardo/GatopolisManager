package br.com.farofa.gm.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseManager {
	private static EntityManagerFactory factory;
	
	public static EntityManagerFactory getFactory () {
		if (factory == null) 
			factory = Persistence.createEntityManagerFactory("gatopolis_v2_db");
		return factory;
	}
	
	public static EntityManager getEntityManager () {
		return getFactory().createEntityManager();
	}
}
