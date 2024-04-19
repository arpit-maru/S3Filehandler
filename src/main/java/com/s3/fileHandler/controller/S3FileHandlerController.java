package com.s3.fileHandler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.s3.fileHandler.dto.ApiResponse;
import com.s3.fileHandler.service.S3FileHandlerService;

@RestController
@RequestMapping("/S3")
@Slf4j
public class S3FileHandlerController {

	@Autowired
	private S3FileHandlerService fileHandlerService;

	@PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ApiResponse uploadFileToS3(@RequestParam("file") MultipartFile multipartFile) {
		log.info("Inside S3FileHandlerController :: uploadFileToS3() method : STARTS");
		return fileHandlerService.uploadFileToS3(multipartFile);
	}
	
	@GetMapping("/getAllDetails")
	public ApiResponse getAllFileDetails() {
		log.info("Inside S3FileHandlerController :: getAllFileDetails() method : STARTS");
		return fileHandlerService.getAllFileLinks();
	}
	
	@GetMapping("/getFileDetailById/{id}")
	public ApiResponse getAllFileDetailById(@PathVariable Integer id) {
		log.info("Inside S3FileHandlerController :: getAllFileDetailById() method : STARTS");
		return fileHandlerService.getFileDetailById(id);
	}

}
