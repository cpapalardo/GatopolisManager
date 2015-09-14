package br.com.farofa.gm.webservice;

import java.util.List;

import br.com.farofa.gm.dao.GameBirdDAO;
import br.com.farofa.gm.dao.GameBirdDAOImpl;
import br.com.farofa.gm.dao.GameSuperCatChallengeDAO;
import br.com.farofa.gm.dao.GameSuperCatChallengeDAOImpl;
import br.com.farofa.gm.database.DatabaseManager;
import br.com.farofa.gm.model.JsonBehaviour;
import br.com.farofa.gm.model.GameBird;
import br.com.farofa.gm.model.GameSuperCatChallenge;

public class blank {
	public String saveOrUpdateGameSuperCatChallenge(String json) {
		String result = null;
		try {
			GameSuperCatChallenge gameSuperCatChallenge = new GameSuperCatChallenge(json);
			GameSuperCatChallengeDAO dao = new GameSuperCatChallengeDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			if (0 == gameSuperCatChallenge.getId())
				dao.save(gameSuperCatChallenge);
			else
				dao.update(gameSuperCatChallenge);
			result = gameSuperCatChallenge.getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		}
		return result;
	}

	public String findGameSuperCatChallengeByInep(String inep) {
		String result = null;
		try {
			GameSuperCatChallengeDAO dao = new GameSuperCatChallengeDAOImpl();
			dao.setEntityManager(DatabaseManager.getEntityManager());
			List<GameSuperCatChallenge> gameSuperCatChallenges = dao.findByInep(inep);
			result = JsonBehaviour.getJsonFromList(gameSuperCatChallenges);
		} catch (Exception e) {
			e.printStackTrace();
			result = WebServiceExeptionManager.getExceptionMessage(e);
		} finally {
			DatabaseManager.close();
		}
		return result;
	}
}
