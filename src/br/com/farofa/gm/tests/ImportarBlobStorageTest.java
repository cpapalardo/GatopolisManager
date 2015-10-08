package br.com.farofa.gm.tests;

import java.io.File;
import java.io.FileInputStream;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

public class ImportarBlobStorageTest {
	public static void main(String[] args) {
		//importToNewStorage();
	}
	
	public static void testImport() {
		// Define the connection-string with your values
		String storageConnectionString = "DefaultEndpointsProtocol=http;"
				+ "AccountName=gatopolis;"
				+ "AccountKey=A9KF2NVT8ksN5xSpEm4NAreRwFOA+Ji5ITT4Bir5T/oiwsHDc5g+AGlQe8bbofW5KWrhT4zOP4VTKqjtBgYyzw==";
		
		try
		{
		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

		    // Create the blob client.
		    CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

		    // Retrieve reference to a previously created container.
		    CloudBlobContainer container = blobClient.getContainerReference("photos");

		    // Loop over blobs within the container and output the URI to each of them.
		    for (ListBlobItem blobItem : container.listBlobs()) {
		    	
		    	/*if (blobItem instanceof CloudBlob) {
		            // Download the item and save it to a file with the same name.
		             CloudBlob blob = (CloudBlob) blobItem;
		             OutputStream os = new FileOutputStream("/users/rodrigosordi/desktop/imagesgatopolis/" + blob.getName());
		             blob.download(os);
		         }*/
		   }
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
	}
	
	public static void importToNewStorage () {
		// Define the connection-string with your values
		String storageConnectionString = "DefaultEndpointsProtocol=http;"
				+ "AccountName=lemann;"
				+ "AccountKey=+KNhW5Xp+LovxzBbqYJLec2TuKy1Do7t+4dRIIrheEusGLrh2F2BtJTKIHB2+1ehX2w5rxsheo8wrQs8ZvbZXA==";
		
		try
		{
		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

		    // Create the blob client.
		    CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

		    // Retrieve reference to a previously created container.
		    CloudBlobContainer container = blobClient.getContainerReference("photos");
		    
		    final File folder = new File("/users/rodrigosordi/desktop/imagesgatopolis");
		    
		    for (final File fileEntry : folder.listFiles()) {
		    	CloudBlockBlob blob = container.getBlockBlobReference(fileEntry.getName());
		        File source = new File(fileEntry.getAbsolutePath());
		        blob.upload(new FileInputStream(source), source.length());
		    	
		    	//System.out.println(fileEntry.getName());
		    	//System.out.println(fileEntry.getAbsolutePath());
		    }

		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
	}
}
