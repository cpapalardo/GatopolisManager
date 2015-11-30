package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/Teacher")
public class TeacherResource {
	private Server server;
	
	public TeacherResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateTeacher")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateTeacher (String json) {
		return server.saveOrUpdateTeacher(json);
	}
	
	@GET
	@Produces("application/json")
	@Path("/findTeacherByInep/{inep}")
	public String findTeacherByInep (@PathParam("inep") String inep) {
		return server.findTeacherByInep(inep);
	}
}
