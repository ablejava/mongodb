package com.my.test;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;

import net.sf.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

/**
 * 读取Properties文件的例子 File: TestProperties.java User: leizhimin Date: 2008-2-15
 * 18:38:40
 */
public class TestProperties {

	public static void main(String args[]) {
//			try {
//				//===================================================//
//				List<ServerAddress> serverList = new ArrayList<ServerAddress>();
//				serverList.add(new ServerAddress("localhost", 27017));
//				//===================================================//
//				List<MongoCredential> mcList = new ArrayList<MongoCredential>();
//				String username = "root";
//				String database = "demo";
//				char[] password = "root123".toCharArray();
//				mcList.add(MongoCredential.createCredential(username, database,password));
//				//===================================================//
//				MongoClientOptions.Builder builder = MongoClientOptions.builder();
//				// 与目标数据库能够建立的最大connection数量为50 
//				builder.connectionsPerHost(50);  
//				// 如果当前所有的connection都在使用中，则每个connection上可以有200个线程排队等待  
//				builder.threadsAllowedToBlockForConnectionMultiplier(200); 
//				// 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟 
//				// 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception 
//				// 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败 
//				builder.maxWaitTime(1000*60*2);  
//				// 与数据库建立连接的timeout设置为1分钟  
//				builder.connectTimeout(1000*60*1);   
//				//===================================================//
//				MongoClientOptions mco = builder.build(); 
//			} catch (Exception e) {
//				
//			}
		        String str = "{\"a\":\"b\", \"c\":\"d\"}";  
		        Object a = JSON.parse(str);  
		        System.out.println(a); // {"c":"d","a":"b"}  
	}
}
