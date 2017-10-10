package com.kx.realdata.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.collect.services.MemoryHandler;
import com.kx.frame.services.MongoDao;
import com.kx.frame.services.MongoDef;
import com.kx.remote.socket.RemoteControl;


@Controller
@RequestMapping("/remote")
public class RemoteController {
	
	@Autowired
	private MongoDao mongoDao;
	@Autowired
	private RemoteControl remoteControl;
	
	@ResponseBody
	@RequestMapping("/chooseopen")//开机选择
	public Object chooseOpen(String _id) {
		if(remoteControl.chooseOpen(_id)) {
			return new Object[] {1,"开机选择成功"};
		}else {
			return new Object[] {0,"开机选择失败"};
		}
	}
	@ResponseBody
	@RequestMapping("/excutopen")//开机执行
	public Object excutOpen(String _id) {
		if(remoteControl.excutOpen(_id)) {
			return new Object[] {1,"开机执行成功"};
		}else {
			return new Object[] {0,"开机执行失败"};
		}
	}
	@ResponseBody
	@RequestMapping("/chooseclose")//关机选择
	public Object chooseClose(String _id) {
		if(remoteControl.chooseClose(_id)) {
			return new Object[] {1,"关机选择成功"};
		}else {
			return new Object[] {0,"关机选择失败"};
		}
	}
	@ResponseBody
	@RequestMapping("/excutclose")//关机执行
	public Object excutClose(String _id) {
		if(remoteControl.excutClose(_id)) {
			return new Object[] {1,"关机执行成功"};
		}else {
			return new Object[] {0,"关机执行失败"};
		}
	}
	
	@ResponseBody
	@RequestMapping("/chooseadjust")//遥调选择
	public Object chooseAdjust(Double value, String _id) {
		if(remoteControl.chooseAdjust(_id, value.floatValue())) {
			return new Object[] {1,"遥调选择成功"};
		}else {
			return new Object[] {0,"遥调选择失败"};
		}
	}
	@ResponseBody
	@RequestMapping("/excutadjust")//遥调选择
	public Object excutAdjust(Double value, String _id) {
		if(remoteControl.excutAdjust(_id, value.floatValue())) {
			return new Object[] {1,"遥调执行成功"};
		}else {
			return new Object[] {0,"遥调执行失败"};
		}
	}
	
	@RequestMapping("/page")
	public String page(String _id, Model model) {
		if(StringUtils.isNotEmpty(_id)) {
			Document doc = mongoDao.findOne(new Document("_id",_id), MongoDef.MEASURE);
			if(doc!=null)model.addAttribute("eqname",doc.get("desc"));
		}
		return "/realdata/remote";
	}
	
	@ResponseBody
	@RequestMapping("/checkpoint")
	public Object checkPoint(String _id) {
		Map<String,Boolean> ds = new HashMap<String,Boolean>();
		ds.put("remote", false);
		if(StringUtils.isNotEmpty(_id)) {
			Document doc = mongoDao.findOne(new Document("_id",_id), MongoDef.MEASURE);
			if(doc!=null) {
				ds.put("remote", true);
			}
		}
		return ds;
	}
	
	@Autowired
	private MemoryHandler memoryHandler;
	
	@ResponseBody
	@RequestMapping("/onedata")
	public Object getValue(String _id) {
		Object val = memoryHandler.getRealValue(_id);
		return new Document("value",val);
	}
}
