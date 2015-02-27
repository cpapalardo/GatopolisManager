package br.com.gatopolismanager.azure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

public class AzureStorageConnectionFactory {
	// Define the connection-string with your values
	public static final String storageConnectionString = 
	    "DefaultEndpointsProtocol=http;" + 
	    "AccountName=gatopolismanager;" + 
	    "AccountKey=3S+eABu3XQZu747vPjfvTKWFkWVDlIgi+Tw2gOUfI5hCDmcYppWjkVv1EGiVsmBRXL27gior9iPvC4LoamofQg==";
	
	private CloudStorageAccount account;
	private CloudBlobClient serviceClient;
	CloudBlobContainer container;
	
	public AzureStorageConnectionFactory () {
		try {
            account = CloudStorageAccount.parse(storageConnectionString);
            serviceClient = account.createCloudBlobClient();

            // Container name must be lower case.
            container = serviceClient.getContainerReference("excel");
            container.createIfNotExists();

		}catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
	
	public void uploadFile (File file) {
		try {
			// Upload an image file.
            CloudBlockBlob blob = container.getBlockBlobReference("GatopolisManager.xls");
            File sourceFile = file; //new File("rodrigosordi/Downloads/GatopolisManager-1.xls");
            blob.upload(new FileInputStream(sourceFile), sourceFile.length());
		}
		catch (StorageException storageException) {
            System.out.print("StorageException encountered: ");
            System.out.println(storageException.getMessage());
            System.exit(-1);
        }
		catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
	}
	
	public void downloadFile (File file) {
		try {
            CloudBlockBlob blob = container.getBlockBlobReference("GatopolisManager.xls");
            blob.downloadToFile(file.getAbsolutePath());
            
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.print("FileNotFoundException encountered: ");
			System.out.println(fileNotFoundException.getMessage());
			System.exit(-1);
		} catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
	}
}
