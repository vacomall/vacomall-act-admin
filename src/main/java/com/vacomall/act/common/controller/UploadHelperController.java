package com.vacomall.act.common.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.vacomall.act.upload.UploadException;
import com.vacomall.act.upload.UploadHelper;
import com.vacomall.act.upload.UploadResult;
/**
 * 图片上传控制器
 * @author GaoJun.Zhou
 * @date 2016年10月17日
 */
public class UploadHelperController extends SuperController{
	
	public static  String allowSuffix = "jpg,png,gif,jpeg";// 允许文件格式
	public static  long allowSize = 5L * 1024 * 1024;// 允许文件大小
	public static Logger logger = Logger.getLogger(UploadHelperController.class);
	
	@Autowired private UploadHelper uploadHelper;
	
	
	
	public static String getAllowSuffix() {
		return allowSuffix;
	}

	public static void setAllowSuffix(String allowSuffix) {
		UploadHelperController.allowSuffix = allowSuffix;
	}

	public static long getAllowSize() {
		return allowSize;
	}

	public static void setAllowSize(long allowSize) {
		UploadHelperController.allowSize = allowSize;
	}

	/**
	 * 上传多个图片
	 * @param files
	 * @param destDir
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String[] uploadImages(MultipartFile[] files, String destDir,
			HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		String[] fileNames = new String[files.length];
		
		int index = 0;
		for (MultipartFile file : files) {
			String suffix = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf(".") + 1);
			int length = getAllowSuffix().indexOf(suffix);
			if (length == -1) {
				throw new Exception("请上传允许格式的文件");
			}
			if (file.getSize() > getAllowSize()) {
				throw new Exception("您上传的文件大小已经超出范围");
			}
			if(file.getSize() <=0){
				throw new UploadException("上传文件大小不能为0");
			}
			File destFile = new File(destDir);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}
			String fileNameNew = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;//
			File f = new File(destFile.getAbsoluteFile() + "\\"
					+ fileNameNew);
			file.transferTo(f);
			f.createNewFile();
			fileNames[index++] = basePath + destDir + fileNameNew;
		}
		
		return fileNames;
	}

	/**
	 * 上传单个图片
	 * @param file
	 * @param destDir
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String uploadImage(MultipartFile file, String destDir,
			HttpServletRequest request) throws Exception {
		return this.uploadImages(new MultipartFile[]{file},destDir,request)[0];
	}
	
	/**
	 * 百度BOS上传
	 * @param files
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	public String[] uploadBosImages(MultipartFile[] files, HttpServletRequest request) throws Exception {

		String[] fileNames = new String[files.length];
		int index = 0;
		for (MultipartFile file : files) {
			
			String suffix = file.getOriginalFilename().substring( file.getOriginalFilename().lastIndexOf(".") + 1);
			int length = getAllowSuffix().indexOf(suffix);
			if (length == -1) {
				throw new UploadException("允许上传格式"+allowSuffix);
			}
			if (file.getSize() > getAllowSize()) {
				throw new UploadException("您上传的文件大小已经超出范围");
			}if(file.getSize() <=0){
				throw new UploadException("上传文件大小不能为0");
			}
			
			String date = DateTime.now().toString("yyyy_MM_dd");;
			String fileNameNew = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
			UploadResult result  = uploadHelper.uploadFile(file.getInputStream(), date+"/"+fileNameNew);
			
			fileNames[index++] = result.getFilePath();
			
			logger.debug("upload success"+","+result.geteTag()+"," + result.getFilePath());
			
		}
		return fileNames; 
		
	}
	
	/**
	 * 百度BOS上传单个文件
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	public String uploadBosImage(MultipartFile file, 
		HttpServletRequest request) throws Exception{
		return this.uploadBosImages(new MultipartFile[]{file}, request)[0];
	}
}
