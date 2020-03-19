package com.amazon.amazon.Controllers;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.amazon.amazon.Services.S3Service;

import Models.UploadFileModel;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AmazonController {
	
	  @Autowired  
	  S3Service s3Services;
	  
	  @Value("${gkz.s3.advbucket}")
	  private String advbucket;
	  

	  @Value("${gkz.s3.userbucket}")
	  private String userbucket;
	  
	
	  
	  	@PostMapping("uploadfile")
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
		    return ResponseEntity.ok()
		          .body(downloadInputStream.toByteArray());  
		  }
	  
	 
	  @GetMapping("/getimg/{key}")
	  public ResponseEntity<byte[]> downloadFile(@PathVariable String key) {
		 System.out.println("fdsdf");
	    ByteArrayOutputStream downloadInputStream = s3Services.downloadFile(key,this.advbucket); 
	    return ResponseEntity.ok()
	          .body(downloadInputStream.toByteArray());  
	  }

	 	 
}
