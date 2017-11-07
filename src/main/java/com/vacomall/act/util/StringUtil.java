package com.vacomall.act.util;

public class StringUtil {

	public static String leftAppend(String text, int length, String string) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<length;i++){
			sb.append(string);
		}
		sb.append(text);
		return sb.toString();
	}

}
