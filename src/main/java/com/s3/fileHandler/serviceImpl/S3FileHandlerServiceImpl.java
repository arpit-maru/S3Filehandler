package com.s3.fileHandler.serviceImpl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.s3.fileHandler.exception.FailedToParseException;
import com.s3.fileHandler.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s3.fileHandler.constant.Messages;
import com.s3.fileHandler.dao.BucketFileDetailDaoImpl;
import com.s3.fileHandler.dto.ApiResponse;
import com.s3.fileHandler.dto.JsonS3Data;
import com.s3.fileHandler.entity.BucketFileDetail;
import com.s3.fileHandler.service.S3FileHandlerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class S3FileHandlerServiceImpl implements S3FileHandlerService {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${aws.bucket-name}")
	private String bucketName;

	@Autowired
	private BucketFileDetailDaoImpl bucketFileDetailDaoImpl;

	private ObjectMapper mapper = new ObjectMapper();

	public ApiResponse uploadFileToS3(MultipartFile multipartFile) {
		log.info("Inside S3FileHandlerServiceImpl :: uploadFileToS3() method : STARTS");
		ApiResponse apiResponse = new ApiResponse();
		try {
			String fileName = multipartFile.getOriginalFilename();
			log.info("uploading file to S3 bucket : {}", bucketName);
			amazonS3.putObject(
					(new PutObjectRequest(this.bucketName, fileName, new ByteArrayInputStream(multipartFile.getBytes()),
							(ObjectMetadata) null)).withCannedAcl(CannedAccessControlList.PublicRead));

			String fileUrl = getUploadedFileS3Link(fileName);
			BucketFileDetail bucketFileDetail = BucketFileDetail.builder().bucketUrl(fileUrl).fileName(fileName).created_at(new Date())
					.build();
			bucketFileDetailDaoImpl.saveBucketFileDetail(bucketFileDetail);

			apiResponse = ApiResponse.builder().message(Messages.FILE_UPLOAD_SUCCESS_MSG).statusCode(HttpStatus.OK.value())
					.build();
		} catch (Exception e) {
			log.error("Failed to upload file to S3 with exception :{}", e.getMessage());
			apiResponse = ApiResponse.builder().message(Messages.SOMETHING_WENT_WRONG)
					.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
		}
		return apiResponse;
	}

	private String getUploadedFileS3Link(String fileName) {
		return amazonS3.getUrl(bucketName, fileName).toString();
	}

	public ApiResponse getAllFileLinks() {
		log.info("Inside S3FileHandlerServiceImpl :: getAllFileLinks() method : STARTS");
		try {
			List<BucketFileDetail> fileDetails = bucketFileDetailDaoImpl.getAllFileLinks();
			return ApiResponse.builder().data(fileDetails).message(Messages.DATA_RETRIEVED_MSG)
					.statusCode(HttpStatus.OK.value()).build();
		} catch (Exception e) {
			log.error("Failed to get data from db with exception : {}", e.getMessage());
			return ApiResponse.builder().message(Messages.SOMETHING_WENT_WRONG)
					.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
		}
	}

	public ApiResponse getFileDetailById(Integer id) {
		log.info("Inside S3FileHandlerServiceImpl :: getFileDetailById() method : STARTS");
		try {
			Optional<BucketFileDetail> fileDetailFromDb = bucketFileDetailDaoImpl.getFileDetailsById(id);
			if (fileDetailFromDb.isEmpty()) {
				return ApiResponse.builder().data(null).message(Messages.DATA_NOT_FOUND)
						.statusCode(HttpStatus.NOT_FOUND.value()).build();
			}
			String rawJsonData = readContentOfFile(fileDetailFromDb.get().getBucketUrl());
			JsonS3Data jsonS3Data = convertStringToObject(rawJsonData);
			return ApiResponse.builder().data(jsonS3Data).message(Messages.DATA_RETRIEVED_MSG)
					.statusCode(HttpStatus.OK.value()).build();
		} catch (IOException e) {
			log.error("Failed to get file/ data with exception   : {}", e.getMessage());
			return ApiResponse.builder().data(null).message(Messages.FAILED_TO_PARSE_FILE)
					.statusCode(HttpStatus.NOT_FOUND.value()).build();
		}
	}

	private JsonS3Data convertStringToObject(String rawJson) {
		JsonS3Data jsonS3Data = null;
		try {
			jsonS3Data = mapper.readValue(rawJson, JsonS3Data.class);
		} catch (Exception e) {
			log.error("Failed to parse value from the file with exception : {} ", e.getMessage());
		}
		return jsonS3Data;
	}
	
	private String readContentOfFile(String fileUrl) throws IOException {
		log.info("downloading the content from the url : {}", fileUrl);
		URL url = new URL(fileUrl);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		String data;
		StringBuilder rawJsonData = new StringBuilder();
		while ((data = reader.readLine()) != null) {
			rawJsonData.append(data);
		}
		reader.close();
		return rawJsonData.toString();
	}
}
