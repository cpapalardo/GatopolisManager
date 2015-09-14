package br.com.farofa.gm.webservice;

import br.com.farofa.gm.azure.BlobStorage;


public class StorageWS {
	
	public String savePhoto(String value) {
		String url = null;
		if (value != null && value.contains(";")) {
			String name = value.split(";")[0];
			String encodedPhoto = value.split(";")[1];
			
			BlobStorage bs = new BlobStorage();
			url = bs.savePhoto(encodedPhoto, name);
		}
		return url;
	}
	
}
