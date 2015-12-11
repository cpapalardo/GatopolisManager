package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/GameSuperCatChallenge")
public class GameSuperCatChallengeResource {

	private Server server;
	
	public GameSuperCatChallengeResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateGameSuperCatChallenge")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateGameSuperCatChallenge(String json) {
		return server.saveOrUpdateGameSuperCatChallenge(json);
	}
	
	@GET
	@Produces("application/json")
	@Path("/findGameSuperCatChallengeByInep/{inep}")
	public String findGameSuperCatChallengeByInep(@PathParam("inep") String inep) {
		return server.findGameSuperCatChallengeByInep(inep);
	}
}