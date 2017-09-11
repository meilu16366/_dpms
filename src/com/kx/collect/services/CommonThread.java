package com.kx.collect.services;

import java.util.Iterator;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kx.frame.def.CollectDef;
import com.kx.frame.services.MongoDao;
import com.kx.frame.services.MongoDef;
import com.kx.frame.sys.beans.Parameter;

/**
 * 电站综合数据线程
 */
@Component("commonThread")
public class CommonThread implements Runnable {
	private static Logger logger = Logger.getLogger(CommonThread.class);
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
				//电站当日累计发电量
				Parameter daycapd = CollectDef.PS_DAYCAP_TYPE;
				String dacapid = "1_" + daycapd.getValue() + "_YC_.*_"+daycapd.getOption1();
			 	dealPSData(dacapid,CollectDef.PS_DAYCAP);
				//电站当前交流总功率
				Parameter acpowerd = CollectDef.PS_ACPOWER_TYPE;
				String acpowerid = "1_" + acpowerd.getValue() + "_YC_.*_"+acpowerd.getOption1();
				dealPSData(acpowerid, CollectDef.PS_ACPOWER);
				 //电站当前直流总功率
				Parameter dcpowerd = CollectDef.PS_DCPOWER_TYPE;
				String dcpowerid = "1_" + dcpowerd.getValue() + "_YC_.*_"+dcpowerd.getOption1();
				dealPSData(dcpowerid, CollectDef.PS_ACPOWER);
				 //逆变器当日累计发电量	
				String nbqdacapid = "1_NBQ_YC_.*_"+CollectDef.INVERTER_NOWDAYCAP;
				dealPSData(nbqdacapid,CollectDef.INVERTER_DAYCAP);
				//逆变器当前总交流功率
				String nbqacpowerd = "1_NBQ_YC_.*_"+CollectDef.INVERTER_NOWACPOWER;
				dealPSData(nbqacpowerd,CollectDef.INVERTER_ACPOWER);
				//逆变器当前总直流功率
				String nbqdcpowerd = "1_NBQ_YC_.*_"+CollectDef.INVERTER_NOWDCPOWER;
				dealPSData(nbqdcpowerd,CollectDef.INVERTER_DCPOWER);
			}catch(Exception e) {
				logger.error("综合数据处理线程出错！");
				e.printStackTrace();
				try {
					Thread.sleep(WEAKUP_TIME);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 处理电站数据
	 * @param dealid 查询id
	 * @param putid put的id
	 */
	private void dealPSData(String dealid,String putid) {
		Document doc = new Document("_id", Pattern.compile("^"+dealid+"$"));
		Iterator<Document> its = mongoDao.find(doc, MongoDef.REALDATA);
		double total = 0.0;
		while(its.hasNext()) {
			Document dc = its.next();
			Object value = dc.get("value");
			if(value != null) {
				if(value instanceof Number) {
					total += ((Number)value).doubleValue();
				}
			}
		}
		memoryHandler.updateCommonRealValue(putid, total);
	}
	
	
	
}
