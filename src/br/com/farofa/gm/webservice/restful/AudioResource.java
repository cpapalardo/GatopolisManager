package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/Audio")
public class AudioResource {

	private Server server;
	
	public AudioResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveAudio")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveAudio(String value){
		return server.saveAudio(value);
	}
}
