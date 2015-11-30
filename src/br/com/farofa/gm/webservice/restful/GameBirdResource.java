package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/GameBird")
public class GameBirdResource {
	
	private Server server;
	
	public GameBirdResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateGameBird")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateGameBird(String json) {
		return server.saveOrUpdateGameBird(json);
	}
	
	@GET
	@Produces("application/json")
	@Path("/findGameBirdByInep/{inep}")
	public String findGameBirdByInep(@PathParam("inep") String inep) {
		return server.findGameBirdByInep(inep);
	}
}
