package com.s3.fileHandler.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s3.fileHandler.entity.BucketFileDetail;
import com.s3.fileHandler.repository.BucketFileDetailRepository;

@Component
public class BucketFileDetailDaoImpl {

	@Autowired
	private BucketFileDetailRepository bucketFileDetailRepository;
	
	public void saveBucketFileDetail(BucketFileDetail bucketFileDetail) {
		bucketFileDetailRepository.save(bucketFileDetail);
	}
	
	public List<BucketFileDetail> getAllFileLinks() {
		return bucketFileDetailRepository.findAll();
	}
	
	public Optional<BucketFileDetail > getFileDetailsById(Integer id) {
		return bucketFileDetailRepository.findById(id);
	}
	
}
