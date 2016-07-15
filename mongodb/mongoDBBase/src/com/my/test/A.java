package com.my.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.util.JSON;
import com.my.base.BaseService;
import com.my.dao.impl.BaseDaoImpl;

public class A extends BaseService {

	public static BaseDaoImpl dao = new BaseDaoImpl();

	public static void main(String[] args) {
		try {
//sour_product_rfidinfo
//			dao.addForJsonStr("aa","{'code':'6907861190212','age':30,'address':'四川成都','aa':'佘大爷'}");
			
//			String jsonStr = "{'code':'6907861190211','age':30,'address':'四川成都','aa':'哇卡卡'}";
//			JSONObject  jsonObject = JSONObject.fromObject(jsonStr);
//			dao.addForJSON("123",jsonObject );
		
//			List li = new ArrayList();
//			for(int i = 0 ; i <100000 ; i++){
//				li.add("{'name':'bb','age':30,'address':'四川成都','numbers':'"+i+"'}");
//			}
//			dao.addBatch("test",li);
			
			//更新
//			 dao.upDataOne("123","6907861190211","OKS","name","aaa");
			//更新全部
			 dao.upDataMany("123","code","6907861190211","code","112");
			 
//			 dao.delOne("testtwo","test", "test");
			 
//           dao.delMany("test","name","bb");

//			JSONArray info= dao.findOne("aa","code","6907861190211");
//			System.out.println(info); 
			
//			List list = dao.findMany("aa", "code","6907861190213");
////			List lista = dao.findAll("testdata");
//			for(Object o :list){
//				System.out.println("allFind输出："+o);
//			}
			
			
			System.out.println(dao.getData());
			List list = dao.findAllToPage("123", 1, 112,1);
			
			for(Object o :list){
				System.out.println("allPage输出："+o);
			}
			System.out.println(dao.getData());
			
//			dao.creatTable("aaaaaaa"); //创建表
//			dao.delTable("test");//删除表

			//关闭
			dao.closeMongoDBConn();
			
//			closeMongoDBConn();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
