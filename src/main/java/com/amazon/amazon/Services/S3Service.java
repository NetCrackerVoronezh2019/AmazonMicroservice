package com.amazon.amazon.Services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {
	
	 @Autowired
	  private AmazonS3 s3client;
	 
	  @Value("${gkz.s3.bucket}")
	  private String bucketName;
	  
	  public void uploadFile(String keyName, MultipartFile file) {
		    try {
		      ObjectMetadata metadata = new ObjectMetadata();
		      metadata.setContentLength(file.getSize());
		      s3client.putObject(bucketName, keyName, file.getInputStream(), metadata);
		    } catch(IOException ioe) {  
		    	System.out.println("EXCEPTION"+ioe.getMessage());
		    } catch (AmazonServiceException ase) {
		    	System.out.println("EXCEPTION"+ase.getMessage());
		        } catch (AmazonClientException ace) {
		        	System.out.println("EXCEPTION"+ace.getMessage());
		        }
		  }
}
