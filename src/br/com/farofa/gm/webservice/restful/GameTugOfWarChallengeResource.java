package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/GameTugOfWarChallenge")
public class GameTugOfWarChallengeResource {

	private Server server;
	
	public GameTugOfWarChallengeResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateGameTugOfWarChallenge")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateGameTugOfWarChallenge(String json) {
		String result = server.saveOrUpdateGameTugOfWarChallenge(json);
		System.out.println("GameTugOfWarChallenge/saveOrUpdateGameTugOfWarChallenge:\n" + result);
		return result;
	}
	
	@GET
	@Produces("application/json")
	@Path("/findGameTugOfWarChallengeByInep/{inep}")
	public String findGameTugOfWarChallengeByInep(@PathParam("inep") String inep) {
		String result = server.findGameTugOfWarChallengeByInep(inep);
		System.out.println("GameTugOfWarChallenge/findGameTugOfWarChallengeByInep:\n" + result);
		return result;
	}
}
