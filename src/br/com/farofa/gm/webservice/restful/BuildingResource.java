package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/Building")
public class BuildingResource {
	
	private Server server;
	
	public BuildingResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateBuilding")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateBuilding(String json) {
		return server.saveOrUpdateBuilding(json);
	}
	
	@GET
	@Produces("application/json")
	@Path("/findBuildingByInep/{inep}")
	public String findBuildingByInep(@PathParam("inep") String inep) {
		return server.findBuildingByInep(inep);
	}
}
