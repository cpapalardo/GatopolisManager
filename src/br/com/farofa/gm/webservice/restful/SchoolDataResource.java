package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/SchoolData")
public class SchoolDataResource {
	
	private Server server;
	
	public SchoolDataResource(){
		server = new Server();
	}
	
	@GET
	@Path("/findSchoolDataByInep/{inep}")
	@Produces("application/json")
	public String findSchoolDataByInep (@PathParam("inep") String inep) {
		String result = server.findSchoolDataByInep(inep);
		System.out.println("ScholData/findSchoolDataByInep" + result);
		return result;
	}
}
