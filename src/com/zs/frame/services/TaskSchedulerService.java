package com.zs.frame.services;


import java.util.Calendar;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zs.frame.sys.beans.TaskScheduler;

@Component("taskSrv")
public class TaskSchedulerService implements ITaskScheduler {

	private static Logger logger = Logger.getLogger(TaskSchedulerService.class);
	private static final String TASK_GROUP = "ZS_GROUP";
	@Autowired
	private Scheduler scheduler;
	
	private IBaseDao daoSrv;
	@Override
	public boolean doTaskJob(TaskScheduler taskScheduler) {
		try {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.SECOND, 5);
			String cron = c.get(Calendar.SECOND)+" "+c.get(Calendar.MINUTE)+" "+c.get(Calendar.HOUR_OF_DAY)+" * * ?";
			String cycle = taskScheduler.getCycle();
			taskScheduler.setCycle(cron);
			try {
				logger.info("5秒后执行任务：" + taskScheduler.getTaskid());
				deleteTaskJob(taskScheduler);
				loadTaskJob(taskScheduler);
				Thread.sleep(10000);
				deleteTaskJob(taskScheduler);
				taskScheduler.setCycle(cycle);
				loadTaskJob(taskScheduler);
				logger.info("删除任务："+taskScheduler.getTaskid());
			} catch (Exception e){
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void loadTaskJob(TaskScheduler taskScheduler) throws Exception {
		if(taskScheduler != null){
			if(CronExpression.isValidExpression(taskScheduler.getCycle())){
				Class clazz = Class.forName(taskScheduler.getClassName());
				JobDetail jobDetail = new JobDetail(taskScheduler.getCode(), TASK_GROUP, clazz);
				CronTrigger trigger = new CronTrigger("task:"+taskScheduler.getName(), TASK_GROUP, taskScheduler.getCycle());
				scheduler.scheduleJob(jobDetail, trigger);
			}else{
				logger.info("无效的作业表达式！taskName:" + taskScheduler.getName());
			}
		}
	}

	@Override
	public void deleteTaskJob(TaskScheduler taskScheduler) throws Exception {
		scheduler.deleteJob(taskScheduler.getCode(), TASK_GROUP);
	}

}
