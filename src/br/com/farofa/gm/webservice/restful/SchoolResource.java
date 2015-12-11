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
		String result = server.saveSchool(json);
		System.out.println(result);
		return result;
	}
	
	@POST
	@Path("/updateSchool")
	@Consumes("application/json")
	@Produces("application/json")
	public String updateSchool (String json) {
		String result = server.updateSchool(json);
		System.out.println(result);
		return result;
	}
	
	@GET
	@Produces("application/json")
	@Path("/findSchoolByInep/{inep}")
	public String findSchoolByInep (@PathParam("inep")  String inep) {
		String result = server.findSchoolByInep(inep);
		System.out.println(result);
		return result;
	}
	
	@GET
	@Produces("application/json")
	@Path("/findSchoolBySyncCode/{syncCode}")
	public String findSchoolBySyncCode (@PathParam("syncCode")  String syncCode) {
		String result = server.findSchoolBySyncCode(syncCode);
		System.out.println(result);
		return result;
	}
	
	@GET
	@Produces("application/json")
	@Path("/findSchoolByName/{name}")
	public String findSchoolByName (@PathParam("name")  String name) {
		String result = server.findSchoolByName(name);
		System.out.println(result);
		return result;
	}
}
