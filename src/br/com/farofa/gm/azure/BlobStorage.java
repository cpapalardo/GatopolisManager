package br.com.farofa.gm.azure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.codec.net.URLCodec;

import br.com.farofa.gm.webservice.WebServiceExeptionManager;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

public class BlobStorage {
	
	private static final String AUDIO_DIR = "d:\\home\\site\\wwwroot\\webapps\\audio";
	private static final String PHOTO_DIR = "d:\\home\\site\\wwwroot\\webapps\\images";
	
	// Define the connection-string with your values
	public static final String storageConnectionString = "DefaultEndpointsProtocol=http;"
			+ "AccountName=gatopolis;"
			+ "AccountKey=A9KF2NVT8ksN5xSpEm4NAreRwFOA+Ji5ITT4Bir5T/oiwsHDc5g+AGlQe8bbofW5KWrhT4zOP4VTKqjtBgYyzw==";
	
//	String storageConnectionString = "DefaultEndpointsProtocol=http;"
//			+ "AccountName=lemann;"
//			+ "AccountKey=+KNhW5Xp+LovxzBbqYJLec2TuKy1Do7t+4dRIIrheEusGLrh2F2BtJTKIHB2+1ehX2w5rxsheo8wrQs8ZvbZXA==";
	
	public static final String containerName = "photos";
	public static final String containerNameAudio = "audio";

	public String savePhoto(String encodedPhoto, String name) {
		String url = null;
		try {
			if (!name.contains(".jpg") && !name.contains(".bmp") && !name.contains(".gif") && !name.contains(".jpeg"))
				name += ".jpg";
			//final String photoPath = "d:\\home\\site\\wwwroot\\webapps\\images\\";
			
			File dir = new File (PHOTO_DIR);
			if (!dir.exists())
				dir.mkdir();
			
			
			name = name.replace(' ', '_');
			File file = new File(PHOTO_DIR + File.separator + name);
			
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
	
	public String saveAudio(String encodedAudioName, String name){
		System.out.println("Entrando em saveAudio");
		
		String url = null;
		try{
			if(!name.contains(".wav"))
				name += ".wav";
			
//			final String audioPath = "d:\\home\\site\\wwwroot\\webapps\\audio\\";
			
			
			//Create audio folder
			File dir = new File (AUDIO_DIR);
			if (!dir.exists())
				dir.mkdir();
			
			name = name.replace(' ', '_');
			//File file = new File(/*audioPath + */name);
			
			File file = new File(AUDIO_DIR + File.separator + name);
			
			URLCodec codec = new URLCodec();
			byte[] data = codec.decode(encodedAudioName.getBytes());
			
			FileOutputStream out = new FileOutputStream(file);
			out.write(data);
			out.close();
			
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
			
			// Create the blob client.
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
			

			// Retrieve reference to a previously created container.
			CloudBlobContainer container = blobClient.getContainerReference(containerNameAudio);
			
			// Create or overwrite the "myimage.jpg" blob with contents from a
			CloudBlockBlob blob = container.getBlockBlobReference(file.getName());
			blob.upload(new FileInputStream (file), file.length());
			
			url = container.getUri() + "/" + file.getName();
			file.delete();
		}catch(Exception e){
			e.printStackTrace();
			return WebServiceExeptionManager.getExceptionMessage(e);
		}
		return url;
	}
}
