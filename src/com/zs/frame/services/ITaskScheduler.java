package com.zs.frame.services;

import java.text.ParseException;

import com.zs.frame.sys.beans.TaskScheduler;


/**
 * 调度作业
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
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
