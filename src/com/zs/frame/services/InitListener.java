package com.zs.frame.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.zs.frame.sys.beans.TaskScheduler;

public class InitListener implements ApplicationListener<ContextRefreshedEvent>{
	
	private static Logger logger = Logger.getLogger(InitListener.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ServiceBean.setApplicationContext(event.getApplicationContext());
		//初始化数据
		logger.info("初始化系统参数开始...");
		
		logger.info("初始化系统参数完成！");
		initJobs();
	}
	/**
	 * 加载调度作业
	 */
	private void initJobs(){
		IBaseDao<TaskScheduler> daoSrv = ServiceBean.getBean("daoSrv");
		ITaskScheduler taskSrv = ServiceBean.getBean("taskSrv");
		List<TaskScheduler> tasks =  daoSrv.findByHql("from TaskScheduler");
		for(TaskScheduler taskScheduler:tasks){
			try {
				if(taskScheduler.getDisabled() != null && !taskScheduler.getDisabled()){
					taskSrv.loadTaskJob(taskScheduler);
					logger.info("加载调度作业："+taskScheduler.getName());
				}
			} catch (Exception e) {
				logger.error("加载调度作业失败 ："+taskScheduler.getName());
			}
		}
	}
}
