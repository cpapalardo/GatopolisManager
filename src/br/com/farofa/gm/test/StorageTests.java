package br.com.farofa.gm.test;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.codec.net.URLCodec;
import org.apache.tomcat.util.codec.binary.Base64;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

public class StorageTests {

	// Define the connection-string with your values
	public static final String storageConnectionString = "DefaultEndpointsProtocol=http;"
			+ "AccountName=gatopolis;"
			+ "AccountKey=A9KF2NVT8ksN5xSpEm4NAreRwFOA+Ji5ITT4Bir5T/oiwsHDc5g+AGlQe8bbofW5KWrhT4zOP4VTKqjtBgYyzw==";

	public static void main(String[] args) {
		testEncodeDecode();
	}
	
	public static void testEncodeDecode() {
		final String filePath = "/users/rodrigosordi/desktop/apple.jpg";
		File file = new File(filePath);
		
		byte[] bFile = new byte[(int) file.length()];
		
		FileInputStream fileInputStream = null;
		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();

			/*for (int i = 0; i < bFile.length; i++) {
				System.out.print((char) bFile[i]);
			}*/

			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		URLCodec codec = new URLCodec();
		System.out.println(bFile[0]);
		
		byte[] encoded = codec.encode(bFile);
		
		System.out.println(encoded[0]);
		System.out.println(encoded.toString());
		
		byte[]test2 = "%20%30".getBytes();
		
	}

	public static void testImage() {
		final String filePath = "/users/rodrigosordi/desktop/apple.jpg";
		File file = new File(filePath);

		byte[] bFile = new byte[(int) file.length()];

		FileInputStream fileInputStream = null;
		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();

			for (int i = 0; i < bFile.length; i++) {
				System.out.print((char) bFile[i]);
			}

			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String imageDataString = Base64.encodeBase64URLSafeString(bFile);
		System.out.println(imageDataString);
	}

	public static void testCreateStorage() {
		try {
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount
					.parse(storageConnectionString);

			// Create the blob client.
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

			// Get a reference to a container.
			// The container name must be lower case
			CloudBlobContainer container = blobClient
					.getContainerReference("mycontainer");

			// Create the container if it does not exist.
			container.createIfNotExists();

			// Create a permissions object.
			BlobContainerPermissions containerPermissions = new BlobContainerPermissions();

			// Include public access in the permissions object.
			containerPermissions
					.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);

			// Set the permissions on the container.
			container.uploadPermissions(containerPermissions);

		} catch (Exception e) {
			// Output the stack trace.
			e.printStackTrace();
		}
	}

	public static void testSendImageToStorage() {
		try {
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount
					.parse(storageConnectionString);

			// Create the blob client.
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

			// Retrieve reference to a previously created container.
			CloudBlobContainer container = blobClient
					.getContainerReference("photos");

			// Define the path to a local file.
			final String filePath = "/users/rodrigosordi/desktop/apple.jpg";

			// Create or overwrite the "myimage.jpg" blob with contents from a
			// local file.
			CloudBlockBlob blob = container.getBlockBlobReference("apple.jpg");
			File source = new File(filePath);
			blob.upload(new FileInputStream(source), source.length());

			System.out.println(container.getUri());
			System.out.println(container.getUri() + "/apple.jpg");
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testReciveImageFromStorage() {
		try {
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount
					.parse(storageConnectionString);

			// Create the blob client.
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

			// Retrieve reference to a previously created container.
			CloudBlobContainer container = blobClient.getContainerReference("photos");

			// Loop through each blob item in the container.
			for (ListBlobItem blobItem : container.listBlobs()) {
				// If the item is a blob, not a virtual directory.
				if (blobItem instanceof CloudBlob) {
					// Download the item and save it to a file with the same
					// name.
					CloudBlob blob = (CloudBlob) blobItem;
					File file = new File("/users/rodrigosordi/desktop/" + blob.getName());
					
					//blob.download(new FileOutputStream("/users/rodrigosordi/desktop/" + blob.getName()));
					
					blob.download(new FileOutputStream(file));
					
					System.out.println(file.getName());
					System.out.println(file.getPath());
				}
			}
		} catch (Exception e) {
			// Output the stack trace.
			e.printStackTrace();
		}
	}
	
	public static void testDownloadImageFromURL() {
		Image image = null;
		try {
			URL urlAdress = new URL("https://gatopolis.blob.core.windows.net/photos/apple.jpg");
			image = ImageIO.read(urlAdress);
			
			InputStream in = new BufferedInputStream(urlAdress.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=in.read(buf)))
			{
			   out.write(buf, 0, n);
			}
			out.close();
			in.close();
			byte[] response = out.toByteArray();
			
			FileOutputStream fos = new FileOutputStream("/users/rodrigosordi/desktop/apple.jpg");
			fos.write(response);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
