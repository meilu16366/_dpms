package com.kx.frame.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.kx.collect.services.ThreadManager;
import com.kx.frame.sys.beans.ListValue;
import com.kx.frame.sys.beans.Parameter;
import com.kx.frame.sys.beans.TaskScheduler;
import com.kx.frame.utils.SysParamUtil;


public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(InitServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
        ServiceBean.setServletContext(getServletContext());
        SysParamUtil.setServletContext(getServletContext());
        //加载值列表
        logger.info("初始化系统参数开始...");
        initListValue();
        //加载系统参数
        initParameter();
		logger.info("初始化系统参数完成！");
		initJobs();
		//启动线程
		ThreadManager.initAllThread();
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

	/**
	 * 加载值列表
	 */
	private void initListValue() {
		IBaseDao<ListValue> daoSrv = ServiceBean.getBean("daoSrv");
		List<ListValue> vals = daoSrv.findByHql("from ListValue order by orderno");
		Map<String,Map<String,ListValue>> listValMap = new LinkedHashMap<String,Map<String,ListValue>>();
		for(ListValue val : vals) {
			Map<String,ListValue> one = listValMap.get(val.getCode());
			if(one == null) {
				one = new LinkedHashMap<String,ListValue>();
				listValMap.put(val.getCode(), one);
			}
			one.put(val.getValue(), val);
		}
		this.getServletContext().setAttribute(SysParamUtil.SYS_LISTVALUE, listValMap);
	}
	/**
	 * 加载系统参数
	 */
	private void initParameter() {
		IBaseDao<Parameter> daoSrv = ServiceBean.getBean("daoSrv");
		List<Parameter> params = daoSrv.findByHql("from Parameter");
		Map<String,Parameter> paramMap = new HashMap<String,Parameter>();
		for(Parameter param:params) {
			paramMap.put(param.getName(), param);
		}
		this.getServletContext().setAttribute(SysParamUtil.SYS_PARAM, paramMap);
	}
}
