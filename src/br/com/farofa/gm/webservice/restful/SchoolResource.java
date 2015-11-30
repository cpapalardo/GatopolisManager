package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/School")
public class SchoolResource {
	
	private Server server;
	
	public SchoolResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveSchool")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveSchool (String json) {
		return server.saveSchool(json);
	}
	
	@POST
	@Path("/updateSchool")
	@Consumes("application/json")
	@Produces("application/json")
	public String updateSchool (String json) {
		return server.updateSchool(json);
	}
	
	@GET
	@Produces("application/json")
	@Path("/findSchoolByInep/{inep}")
	public String findSchoolByInep (@PathParam("inep")  String inep) {
		return server.findSchoolByInep(inep);
	}
	
	@GET
	@Produces("application/json")
	@Path("/findSchoolBySyncCode/{syncCode}")
	public String findSchoolBySyncCode (@PathParam("syncCode")  String syncCode) {
		return server.findSchoolBySyncCode(syncCode);
	}
	
	@GET
	@Produces("application/json")
	@Path("/findSchoolByName/{name}")
	public String findSchoolByName (@PathParam("name")  String name) {
		return server.findSchoolByName(name);
	}
}
