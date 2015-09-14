package br.com.farofa.gm.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseManager {
	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	public static EntityManagerFactory getFactory () {
		if (factory == null) 
			factory = Persistence.createEntityManagerFactory("gatopolis_v2_db");
		return factory;
	}
	
	public static EntityManager getEntityManager () {
		if (em == null || !em.isOpen()) 
				em = getFactory().createEntityManager();
		return em;
	}
	
	public static void close() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}
}
