package com.kx.collect.services;

import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kx.base.beans.Measure;
import com.kx.cache.services.Cache;
import com.kx.da.beans.Eventhis;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.services.MongoDao;
import com.kx.frame.services.MongoDef;


/**
 * 事件处理
 */
@Component("eventThread")
public class EventThread implements Runnable {
	
	private static Logger logger = Logger.getLogger(EventThread.class);
	/**每次查询条数*/
	private static final int LIMIT = 1000;
	/**刷新间隔*/
	private static final long SLEEP_TIME = 1000;
	/**异常重启时间*/
	private static final long WEAKUP_TIME = 30000;
	@Autowired
	private MongoDao mongoDao;
	@Autowired
	private Cache cache;
	@Autowired
	private IBaseDao<Eventhis> daoSrv;
	@Override
	public void run() {
		while(true) {
			try {
				Iterator<Document> its = mongoDao.findAll(MongoDef.EVENT,0,LIMIT);
				while(its.hasNext()) {
					String _id = null;
					Document doc = its.next();
					try {
						_id = doc.get("id")+"";
						String val = doc.get("value")+"";
						String issoe = doc.get("issoe")+"";
						Measure measure = cache.getMeasure(_id);
						
						if(measure != null && "true".equals(measure.getIswarn())) {
							Eventhis his = new Eventhis();
							his.setCollectid(NumberUtils.toInt(measure.getEqid()));
							his.setEqtype(measure.getEqtype());
							String fmt[] = {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss.SSS"};
							Date ctime = DateUtils.parseDate(doc.get("ctime")+"",fmt);
							his.setCtime(ctime);
							his.setEventtype(2);//1操作事件 2遥信变位 3遥测越限 4其他
							his.setDescribes(measure.getDesc());
							int action = val.equals("true")||val.equals("1")?1:0;
							his.setAction(action);
							his.setSure(false);
							his.setIssoe(issoe.equals("true")||issoe.equals("1"));
							his.setElevel(NumberUtils.toInt(measure.getLevel(),1));
							his.setMid(_id);
							daoSrv.saveOrUpdate(his);
						}
					}catch(Exception e) {
						e.printStackTrace();
						logger.error("事件格式错误："+doc);
					}finally {
						if(_id != null) {
							mongoDao.deleteById(_id, MongoDef.EVENT);
						}
					}
				}
			}catch(Exception e) {
				logger.info("事件处理线程错误："+e.getMessage());
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
