package com.kx.cache.services;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.kx.base.beans.InverterStateConf;
import com.kx.base.beans.Measure;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.services.MongoDao;
import com.kx.frame.services.MongoDef;

/**
 * spring缓存
 * @author Administrator
 *
 */
@Component("cache")
public class Cache {
	private static final Logger logger = Logger.getLogger(Cache.class);
	@Autowired
	private IBaseDao daoSrv;
	@Autowired
	private MongoDao mongoDao;
	
	/**
	 * 获得点表数据（缓存读取）
	 * @param _id
	 * @return
	 */
	@Cacheable(value="measure",key="#_id")
	public Measure getMeasure(String _id) {
		Document d = new Document("_id", _id);
		Document rsult = mongoDao.findOne(d, MongoDef.MEASURE);
		Measure m = new Measure();
		for(Field f : Measure.class.getDeclaredFields()) {
			try {
				f.setAccessible(true);
				f.set(m, ""+rsult.get(f.getName()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	
	/**
	 * 获得逆变器状态计算配置monitor.sql
	 * @param model
	 * @return
	 */
	@Cacheable(value="inverterState")
	public InverterStateConf inverterState(String model) {
		String hql = "from InverterStateConf where model='"+model+"'";
		return (InverterStateConf) daoSrv.findOneRowByHql(hql);
	}
	
	/**
	 * 清除所有缓存
	 */
	@CacheEvict(value= {"inverterState","measure"},allEntries=true)
	public void clearAll() {
		logger.info("清除所有spring缓存");
	}
}
