package com.zs.frame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.frame.services.IBaseDao;

/**
 * 通用重复检查
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
@Controller
public class CheckRepeatController {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private IBaseDao daoSrv;
	
	@ResponseBody
	@RequestMapping("/checkrepeat")
	public Object[] check(String fieldValue,String fieldId,String beanName,Model model){
		String hql = "from " + beanName +" where " + fieldId + "='"+fieldValue+"'";
		return new Object[]{fieldId, daoSrv.findByHql(hql).size()==0};
	}
}
