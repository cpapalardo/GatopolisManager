package br.com.farofa.gm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

public class AzureStoreageUtil {
	public static final String storageConnectionString =
	        "DefaultEndpointsProtocol=http;"
	        + "AccountName=gatopolismanager;"
	        + "AccountKey=3S+eABu3XQZu747vPjfvTKWFkWVDlIgi+Tw2gOUfI5hCDmcYppWjkVv1EGiVsmBRXL27gior9iPvC4LoamofQg==";
	
	private CloudStorageAccount account;
	private CloudBlobClient serviceClient;
	private CloudBlobContainer container;
	
	private static final String fileName = "GatopolisManager.xls";
	
	public AzureStoreageUtil () {
		try {
            account = CloudStorageAccount.parse(storageConnectionString);
            serviceClient = account.createCloudBlobClient();

            // Container name must be lower case.
            container = serviceClient.getContainerReference("excel");
            container.createIfNotExists();
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
	
	public void Upload (File file) {
		try {
            // Upload an image file.
            CloudBlockBlob blob = container.getBlockBlobReference(fileName);
            File sourceFile = file; //new File("c:\\myimages\\image1.jpg");
            blob.upload(new FileInputStream(sourceFile), sourceFile.length());
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.print("FileNotFoundException encountered: ");
            System.out.println(fileNotFoundException.getMessage());
            System.exit(-1);
        }
        catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
	}
	
	public File Download () {
		File destinationFile = null;
		try {
            // Download the image file.
			CloudBlockBlob blob = container.getBlockBlobReference(fileName);
			File sourceFile = new File("c:\\Temp\\" + fileName);
            destinationFile = new File(sourceFile.getParentFile(), "image1Download.tmp");
            blob.downloadToFile(destinationFile.getAbsolutePath());
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.print("FileNotFoundException encountered: ");
            System.out.println(fileNotFoundException.getMessage());
            System.exit(-1);
        }
        catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
		return destinationFile;
	}
}
