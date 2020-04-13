package com.amazon.amazon.Controllers;

import java.io.ByteArrayOutputStream;

import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.amazon.amazon.Services.S3Service;

import Models.SendModel;
import Models.UploadFileModel;
import Models.UploadFilesModel;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AmazonController {
	
	  @Autowired  
	  S3Service s3Services;
	  
	  @Value("${gkz.s3.advbucket}")
	  private String advbucket;
	  

	  @Value("${gkz.s3.userbucket}")
	  private String userbucket;
	  
	  @Value("${gkz.s3.documentbucket}")
	  private String documentbucket;
	
	  
	  	@PostMapping("uploadFile")
	    public ResponseEntity<?> uploadAdvImage(@RequestBody UploadFileModel uploadFile) 
	  	{
	  		try {
	  			s3Services.upload(uploadFile.getContent(), uploadFile.getKey(),this.advbucket);
	  			return new ResponseEntity<>(null,HttpStatus.OK);
	  		}
	  		catch(Exception ex)
	  		{
	  			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	  		}
	  	}
	  	
	  	@PostMapping("uploadCertificationFiles")
	  	public ResponseEntity<?> uploadCertificationFiles(@RequestBody UploadFilesModel allFiles)
	  	{
	  		try {
	  			for(UploadFileModel uploadFile:allFiles.allFiles)
	  				s3Services.upload(uploadFile.getContent(), uploadFile.getKey(),this.documentbucket);
	  			return new ResponseEntity<>(null,HttpStatus.OK);
	  		}
	  		catch(Exception ex)
	  		{
	  			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	  		}
	  	}
	  	
	  	
	  	@PostMapping("uploadAdvertisementFiles")
	  	public ResponseEntity<?> uploadAdvertisementFiles(@RequestBody UploadFilesModel allFiles)
	  	{
	  		try {
	  			for(UploadFileModel uploadFile:allFiles.allFiles)
	  				s3Services.upload(uploadFile.getContent(), uploadFile.getKey(),this.advbucket);
	  			return new ResponseEntity<>(null,HttpStatus.OK);
	  		}
	  		catch(Exception ex)
	  		{
	  			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	  		}
	  	}
	  	
	  	
	  	@PostMapping("uploaduserfile")
	    public ResponseEntity<?> uploadUserImage(@RequestBody UploadFileModel uploadFile) 
	  	{
	  		try {
	  			s3Services.upload(uploadFile.getContent(), uploadFile.getKey(),this.userbucket);
	  			return new ResponseEntity<>(null,HttpStatus.OK);
	  		}
	  		catch(Exception ex)
	  		{
	  			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	  		}
	  	}
	  	
	  	
	  	@GetMapping("/getuserimg/{key}")
		  public ResponseEntity<byte[]> downloadUserFile(@PathVariable String key) {
			 System.out.println("fdsdf");
		    ByteArrayOutputStream downloadInputStream = s3Services.downloadFile(key,this.userbucket);
		    SendModel s=new SendModel();
		    return new ResponseEntity<>(downloadInputStream.toByteArray(),HttpStatus.OK);
		  }
		  
	  
	 
	  @GetMapping("/getAdvImg/{key}")
	  public ResponseEntity<byte[]> downloadFile(@PathVariable String key) {
		 System.out.println("fdsdf");
	    ByteArrayOutputStream downloadInputStream = s3Services.downloadFile(key,this.advbucket); 
	    return ResponseEntity.ok()
	          .body(downloadInputStream.toByteArray());  
	  }

	 	 
}
