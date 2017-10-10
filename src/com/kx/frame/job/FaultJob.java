package com.kx.frame.job;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kx.da.beans.AEQdata;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.services.ServiceBean;



/**
 * 故障点事件处理
 * @author Administrator
 *
 */
public class FaultJob implements Job {

	private static Logger logger = Logger.getLogger(FaultJob.class);
	
	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		logger.info("故障点调度开始...");
		try {
			IBaseDao daoSrv = ServiceBean.getBean("daoSrv");
			String sql = "select collectid,eqtype,ctime,eqname,describes,mid,action,sure from "
					+ " (SELECT collectid,eqtype,ctime,eqname,describes,mid,action,sure FROM da_event order by ctime) t "
					+ " group by t.mid having t.action=1 and t.sure='N'";
			List<Object[]> datas = daoSrv.findBySql(sql);
			//type-cid
			Map<String,List<AEQdata>> dataMap = new HashMap<String,List<AEQdata>>();
			for(Object[] os : datas) {
				AEQdata aeq = new AEQdata();
				aeq.setCollectid(NumberUtils.toInt(os[0]+""));
				aeq.setEqtype(os[1]+"");
				aeq.setCtime((Date)os[2]);
				aeq.setEqname(os[3]+"");
				aeq.setDescript(os[4]+"");
				aeq.setKeeptime((double)(System.currentTimeMillis()-aeq.getCtime().getTime())/(60*60*1000));
				List<AEQdata> aeqs = dataMap.get(os[0]+"-"+os[1]);
				if(aeqs == null) {
					aeqs = new ArrayList<AEQdata>();
					dataMap.put(os[0]+"-"+os[1], aeqs);
				}
				aeqs.add(aeq);
			}
			sql = "delete from da_erroreq";
			daoSrv.execSql(sql);
			for(List<AEQdata> aeqs : dataMap.values()) {
				aeqs.sort(null);
				List<String> desc = new ArrayList<String>();
				for(AEQdata aeq:aeqs) {
					desc.add(aeq.getDescript());
				}
				AEQdata ae = aeqs.get(0);
				ae.setDescript(StringUtils.join(desc,","));
				ae.setKeeptime((double) ((new Date().getTime()-ae.getCtime().getTime())/(60*60*1000d)));
				daoSrv.saveOrUpdate(ae);
			}
		}catch(Exception e) {
			logger.info("故障点调度出错！");
			e.printStackTrace();
		}
		logger.info("故障点调度结束！");
	}

}
