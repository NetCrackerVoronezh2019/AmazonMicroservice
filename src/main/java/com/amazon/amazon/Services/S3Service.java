package com.amazon.amazon.Services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;

import Models.GetFileModel;


@Service
public class S3Service {
	
	 @Autowired
	  private AmazonS3 s3client;
	  
	  public void upload(String content,String key,String bucketName,String contentType)
	  {
		  byte[] bytes=org.apache.commons.codec.binary.Base64.decodeBase64((content.substring(content.indexOf(",")+1)).getBytes());
		  ObjectMetadata metadata = new ObjectMetadata();
	      metadata.setContentLength(bytes.length);
		  metadata.setContentType(contentType);
		  InputStream inputStream = new ByteArrayInputStream(bytes);
		  s3client.putObject(bucketName, key, inputStream, metadata);
		  
	  }
	  
	  public void update()
	  {
		 
	  }
	  
	  
	  public void DeleteFile(String key,String bucketName)
	  {
		  try {
			  s3client.deleteObject(bucketName, key);
		  }
		  catch(Exception ex)
		  {
			  
		  }
	  }
	  
	  
	  
	  public void updateFile(String content,String key,String bucketName,String contentType)
	  {
		  this.DeleteFile(key,bucketName);
		  this.upload(content, key,bucketName,contentType);
	  }
	  
	 
	  public  ByteArrayOutputStream downloadFile(String keyName,String bucketName) {
		  	GetFileModel model= new GetFileModel();
		    try {
		            S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, keyName));
		          //  model.contentType=s3object.getObjectMetadata().getContentType();
		            InputStream is = s3object.getObjectContent();
		            ByteArrayOutputStream baos = new ByteArrayOutputStream();
		            int len;
		            byte[] buffer = new byte[4096];
		            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
		                baos.write(buffer, 0, len);
		            }
		            
		            return baos;
		            //return model;
		    } catch (IOException ioe) {
		    			System.out.println(ioe.getMessage());
		        } catch (AmazonServiceException ase) {
		        	System.out.println(ase.getMessage());
		      throw ase;
		        } catch (AmazonClientException ace) {
		        	System.out.println(ace.getMessage());
		            throw ace;
		        }
		    
		    return null;
		  }
	  
}
