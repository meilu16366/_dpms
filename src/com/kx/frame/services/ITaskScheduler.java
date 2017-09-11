package com.kx.frame.services;

import java.text.ParseException;

import com.kx.frame.sys.beans.TaskScheduler;


/**
 * 调度作业
 */
public interface ITaskScheduler {
	
	/**
	 * 调度作业如果存在
	 * @param taskScheduler
	 * @return 调度成功
	 */
	boolean doTaskJob(TaskScheduler taskScheduler);
	
	/**
	 * 加载调度
	 * @param taskScheduler
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 */
	void loadTaskJob(TaskScheduler taskScheduler) throws Exception;
	/**
	 * 删除job
	 * @param taskSchedulers
	 * @throws Exception 
	 */
	void deleteTaskJob(TaskScheduler taskScheduler) throws Exception;
}
