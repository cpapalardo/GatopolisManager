package br.com.farofa.gm.test;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.farofa.gm.dao.SchoolDataDAO;
import br.com.farofa.gm.dao.SchoolDataDAOImpl;
import br.com.farofa.gm.model.SchoolData;

@Named("test")
@RequestScoped
public class DaoTest {
	public static void main(String[] args) {
		testCDI();
	}
	@Inject
	private static SchoolDataDAO dao1;
	
	public static void testCDI () {
		System.out.println(dao1);
	}
	
	public static void testFindByInep() {
		SchoolDataDAO dao = new SchoolDataDAOImpl();
		EntityManager manager = Persistence.createEntityManagerFactory("banco_teste").createEntityManager();
		dao.setEntityManager(manager);
		List<SchoolData> sds = dao.findByInep("12345678");
		for (SchoolData schoolData : sds) {
			System.out.println(schoolData);
		}
	}
}
