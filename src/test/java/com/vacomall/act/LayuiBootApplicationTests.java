package com.vacomall.act;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LayuiBootApplicationTests {

	@Test
	public void contextLoads() {
	}

	/**
	 * 密码加密 测试
	 * @param args
	 */
	@Test
	public  void updatePass() {
		// MD5,"原密码","盐",加密次数
		String pwd =  new SimpleHash("MD5", "123456", "admin", 1024).toString();
		System.out.println(pwd);
		
	}
}
