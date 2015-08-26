package br.com.farofa.gm.database;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("gatopolis_v2_db");
	
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		System.out.println(PersistenceUnitFactory.getPersisteceUnit());
		return factory.createEntityManager();
	}
	
	public void close(@Disposes EntityManager manager) {
		manager.close();
	}
}
