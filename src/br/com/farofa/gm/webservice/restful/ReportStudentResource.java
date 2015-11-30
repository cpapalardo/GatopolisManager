package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/ReportStudent")
public class ReportStudentResource {

	private Server server;
	
	public ReportStudentResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateReportStudent")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateReportStudent(String json) {
		return server.saveOrUpdateReportStudent(json);
	}
	
	@GET
	@Produces("application/json")
	@Path("/findReportStudentByInep/{inep}")
	public String findReportStudentByInep(@PathParam("inep") String inep) {
		return server.findReportStudentByInep(inep);
	}
}
