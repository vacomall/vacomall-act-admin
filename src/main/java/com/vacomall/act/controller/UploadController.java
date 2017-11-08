package com.vacomall.act.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vacomall.act.common.controller.UploadHelperController;

@Controller
@RequestMapping("/upload")
public class UploadController extends UploadHelperController{

	@RequestMapping("/image")
	@ResponseBody
	public Map<String, Object> uploadImage(MultipartFile file){
		 Map<String, Object> map = new HashMap<String, Object>();
		try {
			String url = this.uploadBosImage(file, request);
			map.put("error",0);  
			map.put("url", url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("error",1);  
			map.put("message", e.getMessage());
		}
		return map;
	}

	@GetMapping("/test")
	public String test(){
		return "test/upload";
	}
}
