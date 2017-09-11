package com.kx.collect.services;

import java.util.Iterator;

import org.apache.commons.lang.math.NumberUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kx.frame.services.MongoDao;
import com.kx.frame.services.MongoDef;


/**
 * 实时数据刷新线程
 * @author Administrator
 *
 */
@Component("realTimeDataThread")
public class RealTimeDataThread implements Runnable {
	/**刷新间隔*/
	private static final long SLEEP_TIME = 3000;
	/**异常重启时间*/
	private static final long WEAKUP_TIME = 30000;
	
	@Autowired
	private MemoryHandler memoryHandler;
	@Autowired
	private MongoDao mongoDao;
	
	@Override
	public void run() {
		while(true) {
			try {
				Iterator<Document> docs = mongoDao.findAll(MongoDef.REALDATA);
				while(docs.hasNext()) {
					try {
						Document doc = docs.next();
						String _id = doc.get("_id")+"";
						Object val = doc.get("value");
						if("true".equals(""+val)) {
							memoryHandler.updateRealData(_id, Integer.valueOf(1));
						}else if("false".equals(""+val)) {
							memoryHandler.updateRealData(_id, Integer.valueOf(0));
						}else {
							memoryHandler.updateRealData(_id, NumberUtils.toDouble(val+""));
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}catch(Exception e) {
				try {
					Thread.sleep(WEAKUP_TIME);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}finally {
				try {
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
