package com.zs.frame.services;

import java.util.Iterator;

import org.bson.Document;
import org.bson.conversions.Bson;

public interface MongoDao {
	/**
	 * 查找
	 * @param query 条件
	 * @param table 表名
	 * @return 查询结果
	 */
	Iterator<Document> find(Bson query,String table);
	
	/**
	 * 分段查找
	 * @param query 条件
	 * @param table 表名
	 * @param skip 跳过多少条
	 * @param limit 向后取多少条
	 * @return
	 */
	Iterator<Document> find(Bson query,String table, Integer skip,Integer limit);
	/**
	 * 查找
	 * @param table 表名
	 * @return 查询结果
	 */
	Iterator<Document> findAll(String table);
	/**
	 * 查找
	 * @param table 表名
	 * @param skip 跳过多少条
	 * @param limit 向后取多少条
	 * @return
	 */
	Iterator<Document> findAll(String table, Integer skip,Integer limit );
	
	/**
	 * 查找
	 * @param query 条件
	 * @param table 表名
	 * @return 查询结果
	 */
	Document findOne(Bson query,String table);
	/**
	 * 保存
	 * @param obj
	 */
	void saveOne(Document obj,String table);
	/**
	 * 总数
	 * @param query 条件
	 * @param table 表名
	 * @return 数量
	 */
	long count(Bson query,String table);
	
	/**
	 * 删除符合条件的记录
	 * @param query 条件
	 * @param table 表名
	 */
	void deleteAll(Bson query,String table);
	/**
	 * 根据id删除记录
	 * @param id
	 * @param table 表名
	 */
	void deleteById(Object id,String table);
	/**
	 * 无记录保存否则更新（根据_id 判断有无记录）
	 * @param doc 需保存的Document
	 * @param table 表名
	 */
	void saveOrUpdate(Document doc,String table);
}
