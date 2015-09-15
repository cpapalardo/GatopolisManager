package br.com.farofa.gm.azure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.codec.net.URLCodec;

import br.com.farofa.gatopolisws.webservice.WebServiceExeptionManager;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

public class BlobStorage {
	// Define the connection-string with your values
	public static final String storageConnectionString = "DefaultEndpointsProtocol=http;"
			+ "AccountName=gatopolis;"
			+ "AccountKey=A9KF2NVT8ksN5xSpEm4NAreRwFOA+Ji5ITT4Bir5T/oiwsHDc5g+AGlQe8bbofW5KWrhT4zOP4VTKqjtBgYyzw==";
	
	public static final String containerName = "photos";

	public String savePhoto(String encodedPhoto, String name) {
		String url = null;
		try {
			if (!name.contains(".jpg") && !name.contains(".bmp") && !name.contains(".gif") && !name.contains(".jpeg"))
				name += ".jpg";
			final String photoPath = "d:\\home\\site\\wwwroot\\webapps\\images\\";
			name = name.replace(' ', '_');
			File file = new File(photoPath + name);
			
			URLCodec codec = new URLCodec();
			byte[] data = codec.decode(encodedPhoto.getBytes());
			
			FileOutputStream out = new FileOutputStream(file);
			out.write(data);
			out.close();

			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

			// Create the blob client.
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

			// Retrieve reference to a previously created container.
			CloudBlobContainer container = blobClient.getContainerReference(containerName);

			// Create or overwrite the "myimage.jpg" blob with contents from a
			CloudBlockBlob blob = container.getBlockBlobReference(file.getName());
			blob.upload(new FileInputStream (file), file.length());

			url = container.getUri() + "/" + file.getName();
			file.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
			return WebServiceExeptionManager.getExceptionMessage(e);
		}
		
		return url;
	}
}
