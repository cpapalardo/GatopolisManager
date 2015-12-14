package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/ReportTeacher")
public class ReportTeacherResource {
	
	private Server server;
	
	public ReportTeacherResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateReportTeacher")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateReportTeacher(String json) {
		String result = server.saveOrUpdateReportTeacher(json);
		System.out.println("ReportTeacher/saveOrUpdateReportTeacher:\n" + result);
		return result;
	}
	
	@GET
	@Produces("application/json")
	@Path("/findReportTeacherByInep/{inep}")
	public String findReportTeacherByInep(@PathParam("inep") String inep) {
		String result = server.findReportTeacherByInep(inep);
		System.out.println("ReportTeacher/findReportTeacherByInep:\n" +result);
		return result;
	}
}
