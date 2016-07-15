package com.my.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;


public class properties {
	public static void main(String[] args) {
		
		try {
			Properties property = new Properties();
			property.load(new FileInputStream("src/test.properties"));
			String value = property.getProperty("mail.smtp.host");
			System.out.println(value);
			
//			Properties pps = new Properties();
//			pps.load(new FileInputStream("test.properties"));
//			Enumeration enum1 = pps.propertyNames();// 得到配置文件的名字
//			while (enum1.hasMoreElements()) {
//				String strKey = (String) enum1.nextElement();
//				String strValue = pps.getProperty(strKey);
//				System.out.println(strKey + "=" + strValue);
//			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
