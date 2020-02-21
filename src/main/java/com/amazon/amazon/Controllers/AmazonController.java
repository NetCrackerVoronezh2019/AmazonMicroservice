package com.amazon.amazon.Controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazon.amazon.Models.AmazonUploadModel;
import com.amazon.amazon.Services.S3Service;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AmazonController {
	
	  @Autowired  
	  S3Service s3Services;
	  
	  @PostMapping("uploadfile")
	    public String uploadMultipartFile(@RequestParam("file") MultipartFile _file) {
		  String key="img01";
		  MultipartFile file=_file;
		  System.out.println("KeyName - "+key);
		  if(file!=null) {
			  System.out.println("file is not a NULL");
			  s3Services.uploadFile(key, file);
			  return "Upload Successfully. -> KeyName = " + key;
		  }
		  return "FAIL PEZDA";
	    } 
	  
	  
	 
}
