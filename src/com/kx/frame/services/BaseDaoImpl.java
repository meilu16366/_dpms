package com.kx.frame.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kx.frame.utils.Pager;


/**
 * dao
 */
@Component("daoSrv")
@Transactional
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements IBaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<T> findByHql(String hql) {
		return getSession().createQuery(hql).list();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> findBySql(String sql) {
		return getSession().createSQLQuery(sql).list();
	}

	@Override
	@Transactional(readOnly=true)
	public T findOneRowByHql(String hql) {
		return (T) getSession().createQuery(hql).uniqueResult();
	}

	@Override
	@Transactional(readOnly=true)
	public Object findOneRowBySql(String sql) {
		return getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public void delete(T... t) {
		if(t != null){
			for(T o : t){
				getSession().delete(o);
			}
		}
	}

	@Override
	public void deleteById(Class<T> c, Serializable... ids) {
		if(ids != null){
			for(Serializable id : ids){
				delete((T) getSession().get(c, id));
			}
		}
		
	}

	@Override
	public void save(T... t) {
		if(t != null){
			for(T o : t){
				getSession().save(o);
			}
		}
	}

	@Override
	public void update(T... t) {
		if(t != null){
			for(T o : t){
				getSession().update(o);
			}
		}
	}

	@Override
	public void saveOrUpdate(T... t) {
		if(t != null){
			for(T o : t){
				getSession().saveOrUpdate(o);
			}
		}
	}

	@Override
	public void execSql(String sql) {
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	@Transactional(readOnly=true)
	public Pager<T> findByHql(String hql, String counthql, int pageNo, int pageSize) {
		Pager<T> pager = new Pager<T>(pageNo, pageSize);
		int rowCount = getRowCountByHql(counthql);
		pager.setRowCount(rowCount);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(pageNo > pager.getPageCount()){
			pager.setPageNo(1);
		}
		if(pageNo > 0){
			query.setFirstResult((pageNo - 1) * pageSize);
		}
		if(pageSize > 0){
			query.setMaxResults(pageSize);
		}
		pager.setDatas(query.list());
		return pager;
	}
	
	@Override
	@Transactional(readOnly = true)
	public int getRowCountByHql(String hql){
		int rowCount = 0;
		
		Object o = sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		if( o != null) {
			if(o instanceof Long){
				rowCount = ((Long)o).intValue();
			} else if(o instanceof Integer) {
				rowCount = (Integer)o;
			} else {
				rowCount = Integer.parseInt(o.toString());
			}
		}
		
		return rowCount;
	}

	@Override
	public Object merge(T t) {
		return getSession().merge(t);
	}

	@Override
	public Query getHqlQuery(String hql) {
		return getSession().createQuery(hql);
	}

	@Override
	public List<T> find(Class<T> clazz) {
		return getSession().createCriteria(clazz).list();
	}

	@Override
	public Pager<Object> findBySql(String sql, String countsql, int pageNo, int pageSize) {
		Pager<Object> pager = new Pager<Object>(pageNo, pageSize);
		int rowCount = getRowCountBySql(countsql);
		pager.setRowCount(rowCount);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if(pageNo > pager.getPageCount()){
			pager.setPageNo(1);
		}
		if(pageNo > 0){
			query.setFirstResult((pageNo - 1) * pageSize);
		}
		if(pageSize > 0){
			query.setMaxResults(pageSize);
		}
		pager.setDatas(query.list());
		return pager;
	}

	private int getRowCountBySql(String countsql) {
		int rowCount = 0;
		Object o = sessionFactory.getCurrentSession().createSQLQuery(countsql).uniqueResult();
		if( o != null) {
			if(o instanceof Long){
				rowCount = ((Long)o).intValue();
			} else if(o instanceof Integer) {
				rowCount = (Integer)o;
			} else {
				rowCount = Integer.parseInt(o.toString());
			}
		}
		return rowCount;
	}
}
