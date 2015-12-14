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
	@Path("/updateStudentName")
	public void updateStudentName(){
		server.updateStudentName();
	}
	
	@POST
	@Path("/saveOrUpdateStudent")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateStudent(String json) {
		String result = server.saveOrUpdateStudent(json);
		System.out.println("Student/saveOrUpdateStudent:\n" + result);
		return result;
	}
	
	@GET
	@Produces("application/json")
	@Path("/findStudentByInep/{inep}")
	public String findStudentByInep(@PathParam("inep") String inep) {
		String result = server.findStudentByInep(inep);
		System.out.println("Student/findStudentByInep:\n" + result);;
		return result;
	}
}
