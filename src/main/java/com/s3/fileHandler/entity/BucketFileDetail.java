package com.s3.fileHandler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "file_details")
@NoArgsConstructor
@AllArgsConstructor
public class BucketFileDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "bucket_url")
	private String bucketUrl;
	
	@Column(name = "file_name")
	private String fileName;

	@Column(name = "created_at")
	Date created_at;

}
