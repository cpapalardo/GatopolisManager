package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/GameSuperCat")
public class GameSuperCatResource {
	
	private Server server;
	
	public GameSuperCatResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateGameSuperCat")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateGameSuperCat(String json) {
		String result = server.saveOrUpdateGameSuperCat(json);
		System.out.println(result);
		return result;
	}
	
	@GET
	@Produces("application/json")
	@Path("/findGameSuperCatByInep/{inep}")
	public String findGameSuperCatByInep(@PathParam("inep") String inep) {
		String result = server.findGameSuperCatByInep(inep);
		System.out.println(result);
		return result;
	}
}
