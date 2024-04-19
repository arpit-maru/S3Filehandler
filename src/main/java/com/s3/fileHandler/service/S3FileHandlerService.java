package com.s3.fileHandler.service;

import org.springframework.web.multipart.MultipartFile;

import com.s3.fileHandler.dto.ApiResponse;

public interface S3FileHandlerService {

	public ApiResponse uploadFileToS3(MultipartFile multipartFile);
	
	public ApiResponse getAllFileLinks();
	
	public ApiResponse getFileDetailById(Integer id);
	
}
