package com.s3.fileHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.s3.fileHandler.entity.BucketFileDetail;

@Repository
public interface BucketFileDetailRepository extends JpaRepository<BucketFileDetail, Integer>{

}
