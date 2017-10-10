package com.kx.collect.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.kx.frame.services.ServiceBean;
import com.kx.remote.socket.HeartbeatHandler;


/**
 * 线程管理
 */
public class ThreadManager {

	private static Logger logger = Logger.getLogger(ThreadManager.class);
	/**线程池大小*/
	private static final int POOL_SIZE = 10;
	/**
	 * 启动所有线程
	 */
	public static void initAllThread() {
		ExecutorService excpool = Executors.newFixedThreadPool(POOL_SIZE);
		Runnable realTime =  ServiceBean.getBean("realTimeDataThread");
		excpool.execute(realTime);
		logger.info("实时数据刷新线程启动！");
		Runnable event =  ServiceBean.getBean("eventThread");
		excpool.execute(event);
		logger.info("事件刷新线程启动！");
		Runnable inverterState =  ServiceBean.getBean("inverterStateThread");
		excpool.execute(inverterState);
		logger.info("状态计算线程启动！");
		Runnable common =  ServiceBean.getBean("commonThread");
		excpool.execute(common);
		logger.info("总数据线程启动！");
		HeartbeatHandler.initIpAndPort();
		logger.info("心跳启动");
	}
}
