package com.zs.frame.sys.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.frame.services.IBaseDao;
import com.zs.frame.services.ITaskScheduler;
import com.zs.frame.sys.beans.TaskScheduler;

/**
 * 调度作业
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Controller
@RequestMapping("/sys/task")
public class TaskController {
	
	@Autowired
	private IBaseDao<TaskScheduler> daoSrv;
	
	@RequestMapping("/list")
	public String listTasks(Model model){
		List<TaskScheduler> taskSchedulers = daoSrv.findByHql("from TaskScheduler");
		model.addAttribute("taskSchedulers", taskSchedulers);
		return "/sys/task/listTasks";
	}
	
	@RequestMapping("/save")
	public String saveTask(TaskScheduler taskScheduler) throws Exception{
		daoSrv.saveOrUpdate(taskScheduler);
		taskSrv.deleteTaskJob(taskScheduler);
		if(!taskScheduler.getDisabled()) {
			taskSrv.loadTaskJob(taskScheduler);
		}
		return "redirect:/sys/task/list";
	}
	
	@RequestMapping("/delete")
	public String deleteTasks(String ids){
		if(StringUtils.isNotEmpty(ids)){
			String[] arrid = ids.split(",");
			for(String id : arrid){
				daoSrv.deleteById(TaskScheduler.class, Long.valueOf(id));
			}
		}
		return "redirect:/sys/task/list";
	}
	
	@RequestMapping("/find")
	public String findTask(Long id,Model model){
		if(id != null){
			TaskScheduler taskScheduler = daoSrv.findOneRowByHql("from TaskScheduler where taskid="+id);
			model.addAttribute("taskScheduler",taskScheduler);
		}
		return "/sys/task/editTask";
	}
	
	@Autowired
	private ITaskScheduler taskSrv;
	
	@ResponseBody
	@RequestMapping("/excute")
	public Object doJob(Long id){
		try {
			TaskScheduler taskScheduler = daoSrv.findOneRowByHql("from TaskScheduler where taskid="+id);
			taskSrv.doTaskJob(taskScheduler);
		}catch(Exception e) {
			return 0;
		}
		return 1;
	}
}
