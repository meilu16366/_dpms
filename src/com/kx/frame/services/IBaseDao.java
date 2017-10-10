package com.kx.frame.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import com.kx.frame.utils.Pager;


public interface IBaseDao<T> {
	
	/**
	 * hql查询
	 * @param sql 
	 * @return
	 */
	List<T> findByHql(String hql);
	/**
	 * sql查询
	 * @param sql
	 * @return
	 */
	List<Object> findBySql(String sql);
	/**
	 * hql单行查询
	 * @param hql
	 * @return
	 */
	T findOneRowByHql(String hql);
	/**
	 * sql单行查询
	 * @param sql
	 * @return
	 */
	Object findOneRowBySql(String sql);

	/**
	 * 删除
	 * @param t
	 */
	void delete(T... t);
	/**
	 * 删除
	 * @param c  类型
	 * @param ids
	 */
	void deleteById(Class<T> c,Serializable... ids);
	
	/**
	 * 保存
	 * @param t
	 */
	void save(T... t);
	/**
	 * 更新
	 * @param t
	 */
	void update(T... t);
	/**
	 * 更新或保存
	 * @param t
	 */
	void saveOrUpdate(T... t);
	/**
	 * 执行非查询语句
	 * @param sql
	 */
	void execSql(String sql);
	
	/**
	 * 分页查询
	 * @param hql
	 * @param counthql
	 * @param pageNo
	 * @param pageSize
	 * @return 
	 */
	Pager<T> findByHql(String hql,String counthql,int pageNo,int pageSize);
	/**
	 * 分页查询
	 * @param hql
	 * @param counthql
	 * @param pageNo
	 * @param pageSize
	 * @return 
	 */
	Pager<Object> findBySql(String sql,String countsql,int pageNo,int pageSize);
	/**
	 * 获得总数
	 * @param hql
	 * @return
	 */
	int getRowCountByHql(String hql);
	
	/**
	 * 持久化对象
	 * @param t
	 * @return
	 */
	Object merge(T t);
	/**
	 * 获得查找对象
	 * @param hql
	 * @return
	 */
	Query getHqlQuery(String hql);
	
	/**
	 * 查询
	 * @param clazz
	 * @return
	 */
	List<T> find(Class<T> clazz);
}
