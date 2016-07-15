package com.my.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.bson.BSON;
import org.bson.BsonArray;
import org.bson.Document;
import org.bson.codecs.Decoder;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import com.my.base.BaseService;
import com.my.base.QueryOperators;
import com.my.dao.BaseDao;

/**
 * 基础数据操作方法类
 * 
 * @author Administrator
 * 
 */
public class BaseDaoImpl extends BaseService implements BaseDao {

	/**
	 * 
	 */
	public List<JSONArray> findAll(String tableName) throws Exception {
		getTable(tableName);
		System.out.println("进入查询全部方法");
		BasicDBObject exclude = new BasicDBObject();
		exclude.append("_id", 0);
		// exclude.append("count", 0);
		FindIterable<?> info = table.find().projection(exclude)
				.sort(new BasicDBObject());

		List list = new ArrayList();
		// 遍历结果
		for (Object o : info) {
			JSONArray toJson = JSONArray.fromObject(o);
			list.add(toJson);
		}
		return list;
	}

	/**
	 * 
	 */
	public List<JSONArray> findAllToPage(String tableName, int pageNo,
			int pageSize, int pageLast) throws Exception {
		getTable(tableName);
		int countPage = 0;
		System.out.println("进入查询全部方法findAllToPage");
		BasicDBObject exclude = new BasicDBObject();
		exclude.append("_id", 0);
		// FindIterable<?> info
		// =table.find().projection(exclude).limit(pageSize).skip((pageNo - 1) *
		// 10).sort(searchQuery.append("_id", 1));
		FindIterable<?> info = null;
		if (pageNo == 1) {
			// FindIterable<?> info
			// =table.find().projection(exclude).limit(pageSize).skip((pageNo -
			// 1) * 10).sort(searchQuery.append("_id", 1));
			info = table.find().projection(exclude)// .sort(new
													// BasicDBObject("_id", 1))
					.limit(pageSize);
			table.find();
		} else {
			info = table.find().projection(exclude)
					.skip((pageNo - 1) * pageSize).limit(pageSize);
		}
		// new BasicDBObject("_id", new BasicDBObject(QueryOperators.GT,
		// pageLast))
		List list = new ArrayList();
		// 遍历结果
		for (Object o : info) {
			JSONArray toJson = JSONArray.fromObject(o);
			list.add(toJson);
		}
		return list;
	}

	/**
	 * 单独一个条件查询,返回满足条件的最后一条记录
	 */
	public JSONArray findOne(String tableName, String key, Object value)
			throws Exception {
		getTable(tableName);
		BasicDBObject exclude = new BasicDBObject();
		exclude.append("_id", 0);
		searchQuery.put(key, value);
		searchQuery.put("aa", "哇卡卡2");

		FindIterable info = table.find(searchQuery).projection(exclude);

		System.out.println(info + "--------------");
		if (info == null) {
			return null;
		}
		List list = new ArrayList();
		BSON a = new BSON();
		// info.projection(a);
		// 遍历结果
		JSONArray toJson = null;
		for (Object o : info) {
			toJson = JSONArray.fromObject(o);
			list.add(toJson);
		}
		return toJson;
	}

	/**
	 * 单独一个条件查询
	 */
	public List<JSONArray> findMany(String tableName, String key, Object value)
			throws Exception {
		getTable(tableName);
		BasicDBObject exclude = new BasicDBObject();
		exclude.append("_id", 0);
		// searchQuery.put(key, value);
		FindIterable info = table.find(searchQuery).projection(exclude);
		// System.out.println("---"+info);

		// FindIterable<Object> info = table.find
		// 遍历结果
		List list = new ArrayList<JSONArray>();
		for (Object o : info) {
			JSONArray toJson = JSONArray.fromObject(o);
			list.add(toJson);
		}
		return list;
	}

	public String upDataOne(String tableName, String oldKey, Object oldValue,
			String newKey, Object newValue) throws Exception {
		getTable(tableName);
		System.out.println("进入更新方法");
		BasicDBObject oldInfo = new BasicDBObject();
		oldInfo.put(oldKey, oldValue);

		BasicDBObject newInfo = new BasicDBObject();
		newInfo.put(newKey, newValue);
		// 更新操作
		table.updateOne(oldInfo, new BasicDBObject("$set", newInfo));
		return null;
	}

	public String upDataMany(String tableName, String oldKey, Object oldValue,
			String newKey, Object newValue) throws Exception {
		getTable(tableName);
		System.out.println("进入更新方法");
		BasicDBObject oldInfo = new BasicDBObject();
		oldInfo.put(oldKey, oldValue);

		BasicDBObject newInfo = new BasicDBObject();
		newInfo.put(newKey, newValue);
		// 更新操作

		UpdateResult xx =table.updateMany(oldInfo, new BasicDBObject("$set", newInfo));
		System.out.println("-----00--------"+xx);
		return null;
	}

	public String addForJsonStr(String tableName, String jsonStr)
			throws Exception {
		getTable(tableName);
		Document doc = new Document();
		JSONObject jasonObject = JSONObject.fromObject(jsonStr);
		doc.putAll(jasonObject);
		table.insertOne(doc);
		return null;
	}

	public String addForJSON(String tableName, JSONObject jsonObject)
			throws Exception {
		getTable(tableName);
		System.out.println("进入添加方法");
		Document doc = new Document();
		System.out.println("传入的JSON对象 ：" + jsonObject);
		// System.out.println("JSON字符串转Doc:"+doc.parse(jsonObject));
		doc.putAll(jsonObject);
		table.insertOne(doc);
		return null;
	}

	public String addBatch(String tableName, List jsonList) throws Exception {
		getTable(tableName);
		// 把list里面的JSON类型的字符串转为Document对象再放入LIST进行批量插入操作
		List pp = new ArrayList();
		for (int i = 0; i < jsonList.size(); i++) {
			Document doc = new Document();
			JSONObject aa = JSONObject.fromObject(jsonList.get(i));
			doc.putAll(aa);
			pp.add(doc);
		}
		table.insertMany(pp);
		return null;
	}

	public String addtest(String tableName, List list) throws Exception {
		getTable(tableName);
		List docList = new ArrayList();
		Document doc1 = new Document();
		doc1.put("name", "小李");
		doc1.put("age", 30);
		doc1.put("address", "江苏南京");
		docList.add(doc1);
		Document doc2 = new Document();
		doc2.put("name", "小李");
		doc2.put("age", 30);
		doc2.put("address", "江苏南京");
		docList.add(doc2);

		System.out.println("tojson:+" + doc2.toJson());
		List mpList = new ArrayList();
		Map map1 = new HashMap();
		map1.put("name1", "w0cao1");
		map1.put("sex", "1");
		map1.put("age", "112");
		mpList.add(map1);
		Map map2 = new HashMap();
		map2.put("name2", "w0cao2");
		map2.put("sex", "1");
		map2.put("age", "112");
		mpList.add(map2);

		Document doc3 = new Document();
		BSON bson = new BSON();

		String strJson = "{'name':'佘大爷','age':30,'address':'四川成都aaaaaaaaaaaaaaaaaaaaaa'}";
		JSONObject jasonObject = JSONObject.fromObject(strJson);
		doc3.putAll(jasonObject);

		// Map mapa = (Map)jasonObject;
		// dbList.add(mapa);
		// 把字符串的JSON数据转为Document类型然后装到LIST里面进行批量添加
		System.out.println("开始数据转换" + getData());
		List pp = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Document doc = new Document();
			JSONObject aa = JSONObject.fromObject(list.get(i));
			doc.putAll(aa);
			pp.add(doc);
		}
		System.out.println("完成数据转换" + getData());
		System.out.println("开始数据转插入" + getData());
		table.insertMany(pp);
		System.out.println("完成数据转换插入" + getData());
		// table.insertOne(doc3);
		return null;
	}

	// 删除满足条件的第一条文档
	public String delOne(String tableName, String key, Object value)
			throws Exception {
		getTable(tableName);
		System.out.println("进入单个删除方法");
		Document document = new Document();
		document.put(key, value);
		// table.deleteMany(new Document("borough", "Manhattan"));
		DeleteResult xx = table.deleteOne(document);
		return null;
	}

	// 删除满足条件的所有文档
	public String delMany(String tableName, String key, Object value)
			throws Exception {
		getTable(tableName);
		System.out.println("进入全部删除方法");
		Document document = new Document();
		document.put(key, value);
		// table.deleteMany(new Document("borough", "Manhattan"));
		DeleteResult xx = table.deleteMany(document);
		return null;
	}

	// 删除表
	public String delTable(String tableName) throws Exception {
		getTable(tableName);
		table.drop();
		return null;
	}

	// 创建表
	public String creatTable(String tableName) throws Exception {
		createCollection(tableName);
		return null;
	}

	// 第一种方法，是使用BasicDBObject，方法如下代码所示：
	// BasicDBObject document = new BasicDBObject();
	// document.put("database", "mkyongDB");
	// document.put("table", "hosting");
	// BasicDBObject documentDetail = new BasicDBObject();
	// documentDetail.put("records", "99");
	// documentDetail.put("index", "vps_index1");
	// documentDetail.put("active", "true");
	// document.put("detail", documentDetail);
	// collection.insert(document);
	// 　　第二种方法是使用BasicDBObjectBuilder对象，如下代码所示：
	// 　　BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
	// 　　.add("database", "mkyongDB")
	// 　　.add("table", "hosting");
	// 　　BasicDBObjectBuilder documentBuilderDetail =
	// BasicDBObjectBuilder.start()
	// 　　.add("records", "99")
	// 　　.add("index", "vps_index1")
	// 　　.add("active", "true");
	// 　　documentBuilder.add("detail", documentBuilderDetail.get());
	// 　　collection.insert(documentBuilder.get());
	// 　　第三种方法是使用Map对象，代码如下：
	// 　　Map documentMap =new HashMap();
	// 　　documentMap.put("database", "mkyongDB");
	// 　　documentMap.put("table", "hosting");
	// 　　Map documentMapDetail =new HashMap();
	// 　　documentMapDetail.put("records", "99");
	// 　　documentMapDetail.put("index", "vps_index1");
	// 　　documentMapDetail.put("active", "true");
	// 　　documentMap.put("detail", documentMapDetail);
	// 　　collection.insert(new BasicDBObject(documentMap));
	// 　　第四种方法，也就是最简单的，即直接插入JSON格式数据
	// 　　String json ="{'database' : 'mkyongDB','table' : 'hosting',"+
	// 　　"'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";
	// 　　DBObject dbObject =(DBObject)JSON.parse(json);
	// 　　collection.insert(dbObject);
}
