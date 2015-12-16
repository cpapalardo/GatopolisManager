package br.com.farofa.gm.webservice.restful;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

import br.com.farofa.gm.azure.BlobStorage;

@Path("/Photos")
public class PhotosResource {
	
	@POST
	@Path("/savePhoto")
	@Consumes("multipart/form-data")
	@Produces("application/json")
	public Response saveAudio (FormDataMultiPart form) {
		FormDataBodyPart filePart = form.getField("file");
		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		
		String name = form.getField("name").getValue();
		
		BlobStorage bs = new BlobStorage();
		
		byte[] data;
		try {
			data = IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(500).entity(e.toString()).build();
		}
		
		String result = bs.savePhoto(data, name);
		
		System.out.println("saveAudio/saveAudio:\n" + result);
		
		return Response.status(200).entity(result).build();
	}
}
