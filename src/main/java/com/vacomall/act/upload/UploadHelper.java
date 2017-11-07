package com.vacomall.act.upload;

import java.io.InputStream;

/**
 * 百度存储硬盘操作类
 * @author GaoJun.Zhou
 * @date 2016年10月17日
 */
public interface UploadHelper {
	
	public UploadResult uploadFile(InputStream inputStream,String fileNewName) throws UploadException;

}
