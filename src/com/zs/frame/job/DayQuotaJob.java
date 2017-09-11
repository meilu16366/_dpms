package com.zs.frame.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 日指标job
 * @author Administrator
 *
 */
public class DayQuotaJob implements Job {

	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		//System.out.println(1);
	}

}
