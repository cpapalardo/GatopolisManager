package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/Student")
public class StudentResource {
	
	private Server server;
	
	public StudentResource(){
		server = new Server();
	}
	
	@GET
	@Produces("application/json")
	@Path("/updateStudentName")
	public String updateStudentName(){
		return server.updateStudentName();
	}
	
	@POST
	@Path("/saveOrUpdateStudent")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateStudent(String json) {
		return server.saveOrUpdateStudent(json);
	}
	
	@GET
	@Produces("application/json")
	@Path("/findStudentByInep/{inep}")
	public String findStudentByInep(@PathParam("inep") String inep) {
		return server.findStudentByInep(inep);
	}
}
