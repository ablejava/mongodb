package com.my.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.util.JSON;
import com.my.base.BaseService;
import com.my.dao.impl.BaseDaoImpl;

public class CopyOfA extends BaseService {

	public static BaseDaoImpl dao = new BaseDaoImpl();
	String x= "";
	
	public static void main(String[] args) {

		try {
			int a=2;
			int b=3;
			System.out.println(a|b);
//sour_product_rfidinfo
			dao.addForJsonStr("aa","{'code':'6907861190212','age':30,'address':'四川成都','aa':'佘大爷'}");
			 List<JSONArray>  list = dao.findAll("aa");
			 for (JSONArray jsonArray : list) {
				System.out.println(jsonArray);
			}
//			String jsonStr = "{'code':'6907861190211','age':30,'address':'四川成都','aa':'哇卡卡'}";
//			JSONObject  jsonObject = JSONObject.fromObject(jsonStr);
//			dao.addForJSON("aa",jsonObject );
		
		
//			List li = new ArrayList();
//			for(int i = 0 ; i <100000 ; i++){
//				li.add("{'name':'bb','age':30,'address':'四川成都'}");
//			}
//			dao.addBatch("wocao",li);
			
			
			
			//更新
//			 dao.upDataOne("aa","code","6907861190212","aa","kkk");

//			 dao.delOne("testtwo","test", "test");
			 
//			 dao.delMany("testtwo","test", "test");

//			JSONArray info= dao.findOne("aa","code","6907861190211");
//			System.out.println(info); 
			
//			List list = dao.findMany("aa", "code","6907861190213");
////			List lista = dao.findAll("testdata");
//			for(Object o :list){
//				System.out.println("allFind输出："+o);
//			}
			
			
//			System.out.println(dao.getData());
//			List list = dao.findAllToPage("wocao", 199, 10,1);
//			
//			for(Object o :list){
//					System.out.println("allPage输出："+o);
//			}
//			System.out.println(dao.getData());
//			dao.closeMongoDBConn();
			
//			closeMongoDBConn();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
