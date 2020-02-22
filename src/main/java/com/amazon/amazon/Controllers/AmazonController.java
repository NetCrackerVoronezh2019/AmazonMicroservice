package com.amazon.amazon.Controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.amazon.amazon.Services.S3Service;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AmazonController {
	
	  @Autowired  
	  S3Service s3Services;
	  
	  @PostMapping("uploadfile")
	    public String uploadMultipartFile(@RequestParam("key") String key, @RequestParam("file") MultipartFile _file) {
		  MultipartFile file=_file;
		  if(file!=null) {
			  System.out.println("file is not a NULL");
			  s3Services.uploadFile(key, file);
			  return "Upload Successfully. -> KeyName = " + key;
		  }
		  return "FAIL PEZDA";
	    } 
	 
	  @GetMapping("/getimg")
	  public ResponseEntity<byte[]> downloadFile(String key) {
	    ByteArrayOutputStream downloadInputStream = s3Services.downloadFile("key"); 
	    return ResponseEntity.ok()
	          .body(downloadInputStream.toByteArray());  
	  }
	  
	 
}
