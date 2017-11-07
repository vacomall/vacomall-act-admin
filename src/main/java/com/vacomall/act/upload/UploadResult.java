package com.vacomall.act.upload;

/**
 * 上传文件返回值
 * @author GaoJun.Zhou
 * @date 2016年10月17日
 */

public class UploadResult {

	private String eTag; // 返回MD5校验值
	private String filePath;// 网路路径

	public String geteTag() {
		return eTag;
	}

	public void seteTag(String eTag) {
		this.eTag = eTag;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public UploadResult(String eTag, String filePath) {
		super();
		this.eTag = eTag;
		this.filePath = filePath;
	}

	public UploadResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
