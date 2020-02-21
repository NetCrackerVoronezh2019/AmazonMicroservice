package com.amazon.amazon.Models;

import org.springframework.web.multipart.MultipartFile;

public class AmazonUploadModel {

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	
}
