package br.com.farofa.gm.test;

import java.util.Date;

import br.com.farofa.gm.dao.GameBirdDAO;
import br.com.farofa.gm.dao.GameBirdDAOImpl;
import br.com.farofa.gm.manager.DataBaseManager;
import br.com.farofa.gm.model.GameBird;
import br.com.farofa.gm.model.Student;

public class DaoTests {
	public static void main(String[] args) {
		gameBirdTest();
	}
	
	public static void gameBirdTest() {
		GameBirdDAO dao = new GameBirdDAOImpl(DataBaseManager.getEntityManager());
		
		Student st = new Student();
		st.setId(1);
		GameBird gb = new GameBird(null, true, 1, "", "", new Date(), st);
		
		dao.save(gb);
		
		System.out.println(gb);
	}
}
