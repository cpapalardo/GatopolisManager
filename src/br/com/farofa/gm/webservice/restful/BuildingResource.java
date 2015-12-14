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
		String result = server.saveOrUpdateBuilding(json);
		System.out.println(result);
		return result;
	}
	
	@GET
	@Produces("application/json")
	@Path("/findBuildingByInep/{inep}")
	public String findBuildingByInep(@PathParam("inep") String inep) {
		String result = server.findBuildingByInep(inep);
		System.out.println("Building/findBuildingByInep:\n"+result);
		return result;
	}
}
