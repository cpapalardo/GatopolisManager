package br.com.farofa.gm.manage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataBaseManager {
	private static EntityManager manager;
	
	public static EntityManager getEntityManager () {
		if(manager == null){
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("banco_teste2");
			manager = factory.createEntityManager();
			//factory.close();
		}
		return manager;
	}
}
