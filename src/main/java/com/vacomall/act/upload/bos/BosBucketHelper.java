package com.vacomall.act.upload.bos;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.CannedAccessControlList;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PutObjectRequest;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.baidubce.services.bos.model.SetBucketAclRequest;
import com.vacomall.act.upload.UploadHelper;
import com.vacomall.act.upload.UploadResult;

/**
 * 百度存储硬盘操作类
 * @author GaoJun.Zhou
 * @date 2016年10月17日
 */
@Component
public class BosBucketHelper implements UploadHelper{
	/**
	 * BOS配置
	 */
	@Autowired
	private BosBucketConfig config ;

	
	
	public BosBucketConfig getConfig() {
		return config;
	}



	public void setConfig(BosBucketConfig config) {
		this.config = config;
	}


	@Override
	public UploadResult uploadFile(InputStream inputStream,
			String fileNewName) {

		try {
			/**
			 * 配置
			 */
			BosClientConfiguration bosClientConfiguration = new BosClientConfiguration();
			bosClientConfiguration.setEndpoint(config.getEndpoint());
			bosClientConfiguration.setCredentials(new DefaultBceCredentials(config.getAccessKeyId(),config.getSecretAccessKey()));
			
			/**
			 * 获取client
			 */
			BosClient client = new BosClient(bosClientConfiguration);
			SetBucketAclRequest acl = new SetBucketAclRequest(config.getBucketName(),CannedAccessControlList.PublicRead);
			client.setBucketAcl(acl);
			
			/**
			 * 上传图片
			 */
			PutObjectRequest poRequest = new PutObjectRequest(config.getBucketName(),config.getBucketObject() + fileNewName, inputStream);
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType("image/jpeg;image/png;");
			poRequest.setObjectMetadata(objectMetadata);
			PutObjectResponse pjResponse = client.putObject(poRequest);
			String eTag = pjResponse.getETag();

			String filePath = new StringBuilder(config.getCdnDomain()).append("/")
					.append(config.getBucketObject()).append(fileNewName).toString();
			inputStream.close();
			
			return new UploadResult(eTag,filePath);
			
		} catch (Exception e) {
			
			throw new RuntimeException(e.getMessage());
		}
	}

}
