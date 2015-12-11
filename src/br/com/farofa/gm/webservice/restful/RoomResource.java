package br.com.farofa.gm.webservice.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.farofa.gm.webservice.Server;

@Path("/Room")
public class RoomResource {

	private Server server;
	
	public RoomResource(){
		server = new Server();
	}
	
	@POST
	@Path("/saveOrUpdateRoom")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveOrUpdateRoom(String json) {
		String result = server.saveOrUpdateRoom(json);
		System.out.println(result);
		return result;
	}
	
	@GET
	@Produces("application/json")
	@Path("/findRoomByInep/{inep}")
	public String findRoomByInep(@PathParam("inep") String inep) {
		String result = server.findRoomByInep(inep);
		return result;
	}
}
