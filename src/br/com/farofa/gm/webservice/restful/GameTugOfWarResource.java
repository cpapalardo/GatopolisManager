package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/GameTugOfWar")
public class GameTugOfWarResource {
	
	private Server server;
	
	public GameTugOfWarResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateGameTugOfWar")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateGameTugOfWar(String json) {
		String result = server.saveOrUpdateGameTugOfWar(json);
		System.out.println("GameTugOfWar/saveOrUpdateGameTugOfWar:\n" + result);
		return result;
	}
	
	@GET
	@Produces("application/json")
	@Path("/findGameTugOfWarByInep/{inep}")
	public String findGameTugOfWarByInep(@PathParam("inep") String inep) {
		String result = server.findGameTugOfWarByInep(inep);
		System.out.println("GameTugOfWar/findGameTugOfWarByInep:\n" +result);
		return result;
	}
}
