package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/Transition")
public class TransitionResource {
	
	private Server server;
	
	public TransitionResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateTransition")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateTransition(String json) {
		String result = server.saveOrUpdateTransition(json);
		System.out.println("Transition/saveOrUpdateTransition:\n" + result);
		return result;
	}
	
	@GET
	@Produces("application/json")
	@Path("/findTransitionByInep/{inep}")
	public String findTransitionByInep(@PathParam("inep") String inep) {
		String result = server.findTransitionByInep(inep);
		System.out.println("Transition/findTransitionByInep:\n" + result);
		return result; 
	}
}
