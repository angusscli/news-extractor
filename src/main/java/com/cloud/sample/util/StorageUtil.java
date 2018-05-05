package com.cloud.sample.util;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobListOption;
import com.google.cloud.storage.Storage.BucketListOption;
import com.google.cloud.storage.StorageOptions;

public class StorageUtil {
	public final static String BUCKET_NAME = "staging-testing-43541281";

	private final static Logger log = Logger.getLogger(StorageUtil.class.getName());
	
	public static void write(String fileName, String content) throws IOException {
		InputStream keyStream = StorageUtil.class.getClassLoader().getResourceAsStream("key.json");
		
		// Define the Google cloud storage
		Storage storage = StorageOptions.newBuilder()
			    //.setCredentials(ServiceAccountCredentials.fromStream(keyStream))
			    .build()
			    .getService();

		// Upload a file to bucket
		BlobInfo blobInfo =
			      storage.create(
			          BlobInfo
			              .newBuilder(StorageUtil.BUCKET_NAME, fileName)
			              // Modify access list to allow all users with link to read file
			              //.setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
			              .build(),
			              new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
	}
	
	public static String read(String file) {
		Storage storage = StorageOptions.newBuilder()
			    .build()
			    .getService();
		Blob blob = storage.get(StorageUtil.BUCKET_NAME, file);
		String fileContent = new String(blob.getContent());

		return fileContent;
	}
	
	
	public static List<String> list(String folder, String extension) {
		// Define the Google cloud storage
		Storage storage = StorageOptions.newBuilder()
			    //.setCredentials(ServiceAccountCredentials.fromStream(keyStream))
			    .build()
			    .getService();
		
		List<String> list = new ArrayList<String>();
			
		for (Bucket bucket : storage.list(BucketListOption.prefix(StorageUtil.BUCKET_NAME)).iterateAll()) {
		  // List all blobs in the bucket
		  for (Blob blob : bucket.list(BlobListOption.prefix(folder)).iterateAll()) {
		    //System.out.println(blob.getName());
			  if (blob.getName().endsWith(extension)) {
				    list.add(blob.getName());
			  }
		  }
		}
		return list;
	}
}
