package com.kx.collect.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kx.base.beans.Inverter;
import com.kx.base.beans.InverterStateConf;
import com.kx.cache.services.Cache;
import com.kx.frame.def.CollectDef;
import com.kx.frame.services.IBaseDao;


/**
 * 状态计算线程
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Component("inverterStateThread")
public class InverterStateThread implements Runnable{
	
	@Autowired
	private Cache cache;
	@Autowired
	private IBaseDao<Inverter> daoSrv;
	@Autowired
	private MemoryHandler memoryHandler;
	
	/**刷新间隔*/
	private static final long SLEEP_TIME = 1000;
	/**异常重启时间*/
	private static final long WEAKUP_TIME = 1500;
	/**所有逆变器信息*/
	private static List<Inverter> inverters;
	
	@Override
	public void run() {
		inverters = daoSrv.findByHql("from Inverter");
		//TODO 逆变器型号字段必填
		while(true) {
			
			for(Inverter inverter : inverters) {
				try {
					//获得状态配置
					InverterStateConf conf = cache.inverterState(inverter.getModels());
					if(conf == null) {
						continue;
					}
					//1 正常运行，2正常停机，3故障运行，4故障停机，5通讯中断
					int nowState = 0;
					String firstid = "1_NBQ_YX_" + inverter.getCollectid() + "_" ;
					//正常运行
					String run = conf.getNormalrun();
					if(nowState == 0) {
						nowState = dealOnePoint(firstid,run)?1:0;
					}
					//正常停机
					String stop = conf.getNormalstop();
					if(nowState == 0) {
						nowState = dealOnePoint(firstid,stop)?2:0;
					}
					//告警运行
					String fault = conf.getErrorrun();
					if(nowState == 0) {
						nowState = dealOnePoint(firstid,fault)?3:0;
					}
					//故障停机
					String faultStop = conf.getErrorstop();
					if(nowState == 0) {
						nowState = dealOnePoint(firstid,faultStop)?4:0;
					}
					//通讯中断
					String bread = conf.getBreakPoint(inverter.getCollectid());
					if(StringUtils.isNotEmpty(bread)) {
						Object val =  memoryHandler.getRealValue(bread);
						if(val!=null && NumberUtils.toInt(val+"")==0) {
							nowState = 6;
						}
					}
					memoryHandler.putState("1_NBQ_"+inverter.getCollectid()+"_"+CollectDef.INVERTER_STATE, Integer.valueOf(nowState));
				}catch(Exception e) {
					e.printStackTrace();
					try {
						Thread.sleep(WEAKUP_TIME);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
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
	 * 处理1到4状态
	 * @param firstid
	 * @param point
	 * @return
	 */
	private boolean dealOnePoint(String firstid,String point) {
		if(StringUtils.isNotEmpty(point)) {
			String[] ids = point.split(",");
			for(String one : ids) {
				String id = firstid + one;
	
				Object val =  memoryHandler.getRealValue(id);
				if(val != null) {
					val = Double.valueOf(val+"").intValue();
				}
				if(val == Integer.valueOf(1)) {
					return true;
				}
			}
		}
		return false;
	}
}
