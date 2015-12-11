package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/Note")
public class NoteResource {
	
	private Server server;
	
	public NoteResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateNote")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateNote(String json) {
		return server.saveOrUpdateNote(json);
	}
	
	@GET
	@Produces("application/json")
	@Path("/findNoteByInep/{inep}")
	public String findNoteByInep(@PathParam("inep") String inep) {
		return server.findNoteByInep(inep);
	}
}