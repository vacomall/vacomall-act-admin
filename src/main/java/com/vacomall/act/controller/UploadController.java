package com.vacomall.act.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vacomall.act.common.controller.UploadHelperController;

@Controller
@RequestMapping("/upload")
public class UploadController extends UploadHelperController{

	@PostMapping("/image")
	@ResponseBody
	public Map<String, Object> uploadImage(MultipartFile file){
		 Map<String, Object> map = new HashMap<String, Object>();
		try {
			String url = this.uploadBosImage(file, request);
			map.put("state", "SUCCESS");  
			map.put("url", url);  
			map.put("size", file.getSize());  
			map.put("original", file.getOriginalFilename());  
			map.put("type", file.getContentType()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("state", "ERROR"); 
			map.put("error",e.getMessage());
		}
		return map;
	}

	@GetMapping("/test")
	public String test(){
		return "test/upload";
	}
}
