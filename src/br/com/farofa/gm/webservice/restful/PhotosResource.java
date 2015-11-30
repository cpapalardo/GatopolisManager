package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/Photos")
public class PhotosResource {

	private Server server;
	
	public PhotosResource(){
		server = new Server();
	}
	
	@POST
	@Path("/savePhoto")
	@Consumes("application/json")
	@Produces("application/json")
	public String savePhoto(String value) {
		return server.savePhoto(value);
	}
}
