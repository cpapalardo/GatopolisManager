package br.com.farofa.gm.azure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.codec.net.URLCodec;

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
			if (!name.contains(".jpg") && !name.contains(".bmp") && !name.contains(".gif"))
				name = name + ".jpg";
			
			File photo = new File(name);
			
			URLCodec codec = new URLCodec();
			byte[] data = codec.decode(encodedPhoto.getBytes());
			
			@SuppressWarnings("resource")
			FileOutputStream stream = new FileOutputStream(photo);
			stream.write(data);
			
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

			// Create the blob client.
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

			// Retrieve reference to a previously created container.
			CloudBlobContainer container = blobClient.getContainerReference(containerName);

			// Create or overwrite the "myimage.jpg" blob with contents from a
			CloudBlockBlob blob = container.getBlockBlobReference(photo.getName());
			blob.upload(new FileInputStream(photo), photo.length());

			url = container.getUri() + "/" + photo.getName();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}
}
