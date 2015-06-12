package br.com.farofa.gm.webservice;

import br.com.farofa.gm.azure.BlobStorage;

public class StorageWS {
	
	public String savePhoto(String encodedPhoto, String name) {
		BlobStorage bs = new BlobStorage();
		String url = bs.savePhoto(encodedPhoto, name);
		return url;
	}
	
}
