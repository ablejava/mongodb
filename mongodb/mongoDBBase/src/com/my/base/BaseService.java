package com.my.base;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import sun.security.jca.GetInstance.Instance;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 数据库连接类
 * @author Administrator
 *
 */
public class BaseService {
	//数据库对象
	public static MongoClient mongoClient = null;
	///根据MongoClient数据库的名称获取到的MongoDatabase对象 
	public static MongoDatabase mdb = null;
	@SuppressWarnings("rawtypes")
	public static MongoCollection table = null;
	// Mongo数据库地址
	private static String host = "";
	// Mongo数据库默认端口号
	private static String port = "";
	// Mongo数据库用户名
	private static String username = "";
	// Mongo数据库用户密码
	private static String password = "";
	// Mongo数据库名字
	private static String dbname = "";
	// 查询条件
	public static BasicDBObject searchQuery = new BasicDBObject();

	/**
     * 根据名称获取DB，相当于是连接
     * 
     * @param dbName
     * @return
     */
    public static MongoDatabase getDB() {
        if (mdb == null) {
            // 初始化
        	System.out.println("调用初始化----------------------");
            init();
        }else{
        	System.out.println("no null--------------------------------------------");
        }
        return mongoClient.getDatabase("test");
    }
	
	private static void init() {
		System.out.println("null------------------------------------");
	}

	 static{
	        loads();
	    }
	/**
	 * 初始化数据库连接
	 */
	 synchronized static public void loads() {
		try {
			Properties property = new Properties();
			property.load(new FileInputStream("src/mongodb.properties"));
			host = property.getProperty("mdb.host");//数据库主机地址
			port = property.getProperty("mdb.port");//数据库端口号
			username = property.getProperty("mdb.username");//数据库登录用户名
			password = property.getProperty("mdb.password");//数据库登录密码
			dbname = property.getProperty("mdb.dbname");//要连接的数据库名字
			// 服务器名称和端口号对象  ServerAddress("数据库地址"，端口号);
			ServerAddress addr = new ServerAddress(host, Integer.parseInt(port));
			//把创建的连接加入list集合
			List<ServerAddress> serAddList = new ArrayList<ServerAddress>();
			serAddList.add(addr);
			//数据库的身份验证对象MongoCredential    用户名  数据库   密码
			MongoCredential credential = MongoCredential.createCredential(username, dbname, password.toCharArray());
			List<MongoCredential> credentialList = new ArrayList<MongoCredential>();
			credentialList.add(credential);
			//设置连接池的属性值
			MongoClientOptions.Builder options = MongoClientOptions.builder();
			// 连接池连接数量
			options.connectionsPerHost(500);
			// 连接超时，推荐3000毫秒
			options.connectTimeout(15000);
			// 最大等待时间
			options.maxWaitTime(15000);
			// 套字节超时时间，0无限制
			options.socketTimeout(15000);
			//如果当前所有的connection都在使用中，则每个connection上可以有200个线程排队等待  
			options.threadsAllowedToBlockForConnectionMultiplier(200);
			//===================================================//
			MongoClientOptions mco = options.build(); 
			mongoClient = new MongoClient(serAddList, credentialList,mco);
			//得到数据库连接，getDatabase数据库名
			mdb = mongoClient.getDatabase(dbname);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		getDB();
	}

	/**
	 * 返回一个表连接
	 * @param tableName  需要操作的表数据库表名字
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static MongoCollection getTable(String tableName) {
		table = mdb.getCollection(tableName);
		return table;
	}
	@SuppressWarnings("rawtypes")
	public static void createCollection(String tableName) {
		mdb.createCollection(tableName);
	}
	
	/**
	 * 关闭MongoDB连接
	 */
	public static void closeMongoDBConn() {
		if (mdb != null) {
			mdb = null;
		}else{
			System.out.println("mdb is null");
		}
		if (mongoClient != null) {
			mongoClient.close();
		}else{
			System.out.println("mongoClient is null");
		}
	}

	public String getUUID() {
		return UUID.randomUUID().toString();
	}

	public Timestamp getData() {
		// new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") 设置时间日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ms");// 设置日期格式
		// new Date()为获取当前系统时间
		String time = df.format(new Date());
		String xx = new Date().toString();
		
		Timestamp d = new Timestamp(System.currentTimeMillis());
		return d;
	}

}
