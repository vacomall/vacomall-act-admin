package com.vacomall.act.upload.bos;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * BOS配置
 * @author GaoJun.Zhou
 * @date 2016年10月17日
 */
@Configuration
public class BosBucketConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * accessKeyId
	 */
	@Value("${bos.accessKeyId}")
	private String accessKeyId;
	/**
	 * secretAccessKey
	 */
	@Value("${bos.secretAccessKey}")
	private String secretAccessKey;
	/**
	 * endpoint
	 */
	@Value("${bos.endpoint}")
	private String endpoint;
	/**
	 * bucketName
	 */
	@Value("${bos.bucketName}")
	private String bucketName;
	/**
	 * bucketObject
	 */
	@Value("${bos.bucketObject}")
	private String bucketObject;
	/**
	 * cdnDomain
	 */
	@Value("${bos.cdnDomain}")
	private String cdnDomain;

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getSecretAccessKey() {
		return secretAccessKey;
	}

	public void setSecretAccessKey(String secretAccessKey) {
		this.secretAccessKey = secretAccessKey;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getBucketObject() {
		return bucketObject;
	}

	public void setBucketObject(String bucketObject) {
		this.bucketObject = bucketObject;
	}

	public String getCdnDomain() {
		return cdnDomain;
	}

	public void setCdnDomain(String cdnDomain) {
		this.cdnDomain = cdnDomain;
	}

	public BosBucketConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BosBucketConfig(String accessKeyId, String secretAccessKey,
			String endpoint, String bucketName, String bucketObject,
			String cdnDomain) {
		super();
		this.accessKeyId = accessKeyId;
		this.secretAccessKey = secretAccessKey;
		this.endpoint = endpoint;
		this.bucketName = bucketName;
		this.bucketObject = bucketObject;
		this.cdnDomain = cdnDomain;
	}

}
