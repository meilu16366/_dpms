package com.kx.frame.job;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kx.da.services.InverterDayQuota;
import com.kx.da.services.PSDayQuota;
import com.kx.frame.services.ServiceBean;


/**
 * 日指标job
 * @author Administrator
 *
 */
public class DayQuotaJob implements Job {
	private static Logger logger = Logger.getLogger(DayQuotaJob.class);
	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		logger.info("逆变器日报计算开始...");
		InverterDayQuota inverterquota = ServiceBean.getBean("inverterDayQuota");		
		inverterquota.calc(null, c.getTime());
		logger.info("逆变器日报计算完成！");
		
		logger.info("电站生产日报计算开始...");
		PSDayQuota psquota = ServiceBean.getBean("psDayQuota");
		psquota.calc(c.getTime());
		logger.info("电站生产日报计算结束！");
	}

}
