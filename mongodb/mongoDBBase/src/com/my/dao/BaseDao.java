package com.my.dao;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.client.FindIterable;
/**
 * 基础数据操作方法接口
 * @author Administrator
 *
 */
public interface BaseDao {
	
	
	/**
	 * 查询全部
	 * @param tableName 需要操作的表名
	 * @return 返回一个list里面装有JSON格式的数据
	 * @throws Exception
	 */
	public  List<JSONArray> findAll(String tableName) throws Exception;
	/**
	 * 分页查询全部
	 * @param tableName 需要操作的表名
	 * @return 返回一个list里面装有JSON格式的数据
	 * @param pageNo 显示第几页
	 * @param pageSize 每页显示的记录数
	 * @return
	 * @throws Exception
	 */
	public  List<JSONArray> findAllToPage(String tableName,int pageNo,int pageSize,int pageLast) throws Exception;
	
	/**
	 * 查询出满足条件的第一条文档
	 * @param tableName 需要操作的表名
	 * @param key 条件key值
	 * @param value 条件value值
	 * @return
	 * @throws Exception
	 */
	public JSONArray findOne(String tableName,String key,Object value) throws Exception;
	
	/**
	 * 
	 * 查询出满足条件的全部文档
	 * @param tableName 需要操作的表名
	 * @param key 条件key值
	 * @param value 条件value值
	 * @return
	 * @throws Exception
	 */
	public List<JSONArray>  findMany(String tableName,String key,Object value) throws Exception;
	
	/**
	 * 单个数据添加可以把JSON字符串直接添加到数据库
	 * @param tableName 要操作数据的目标表名
	 * @param jsonStr  传入JSON格式的数据字符串
	 * @return
	 * @throws Exception
	 */
	public String addForJsonStr(String tableName,String jsonStr) throws Exception;
	
	/**
	 * 单个数据添加可以把JSON对象直接添加到数据库
	 * @param tableName 要操作数据的目标表名
	 * @param jsonObject  传入JSON对象
	 * @return
	 * @throws Exception
	 */
	public String addForJSON(String tableName,JSONObject jsonObject) throws Exception;
	
	
	/**
	 *批量添加
	 * @param tableName 要操作数据的目标表名
	 * @param jsonObject  传入JSON对象
	 * @return
	 * @throws Exception
	 */
	public String addBatch(String tableName,List jsonList) throws Exception;
	
	
	/**
	 * 单个更新满足条件的第一条数据
	 * @param oldKey 要更新的旧数据key值       条件
	 * @param oldValue  要更新的旧数据value值       条件
	 * @param newKey  新数据的key值  如原数据没有对应的key则新增一个key
	 * @param newValue 新数据的value值
	 * @return
	 * @throws Exception
	 */
	public String upDataOne(String tableName,String oldKey,Object oldValue,String newKey,Object newValue) throws Exception;
	
	/**
	 * 单个更新，满足条件的全部数据
	 * @param oldKey 要更新的旧数据key值       条件
	 * @param oldValue  要更新的旧数据value值       条件
	 * @param newKey  新数据的key值  如原数据没有对应的key则新增一个key
	 * @param newValue 新数据的value值
	 * @return
	 * @throws Exception
	 */
	public String upDataMany(String tableName,String oldKey,Object oldValue,String newKey,Object newValue) throws Exception;
	
	/**
	 * 删除满足条件的第一个文档
	 * @param key 要删除的数据key值
	 * @param value 要删除的数据value值
	 * @return 
	 * @throws Exception
	 */
	public String delOne(String tableName,String key,Object value) throws Exception;
	

	/**
	 * 删除满足条件的全部文档
	 * @param key 要删除的数据key值
	 * @param value 要删除的数据value值
	 * @return 
	 * @throws Exception
	 */
	public String delMany(String tableName,String key,Object value) throws Exception;
	
	/**
	 * 
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public String delTable(String tableName) throws Exception;
	

}
