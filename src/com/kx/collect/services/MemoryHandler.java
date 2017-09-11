package com.kx.collect.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kx.frame.def.CollectDef;


/**
 * 内存数据处理
 */
@Component("memoryHandler")
public class MemoryHandler {
	/**所有实时数据*/
	private Map<String,Object> realDataMap = new HashMap<String,Object>();
	/**逆变器实时状态*/
	private Map<String,Object> stateMap = new HashMap<String,Object>();
	/**电站实时数据*/
	private Map<String,Object> commonrealDataMap = new HashMap<String, Object>();

	/**
	 * 填数据
	 * @param key
	 * @param value
	 */
	public void updateRealData(String key,Object value) {
		realDataMap.put(key, value);
	}
	/**
	 * 获取实时数据
	 * @param _id
	 * @return
	 */
	public Object getRealValue(String _id) {
		Object value = null;
		if(_id.indexOf(CollectDef.INVERTER_STATE)>-1) {
			value = stateMap.get(_id);
		}else {
			value = realDataMap.get(_id);
		}
		return value;
	}
	/**
	 * 获取电站实时数据
	 * @param _id
	 * @return
	 */
	public Object getCommonRealValue(String _id) {
		return commonrealDataMap.get(_id);
	}
	/**
	 * 更新电站数据
	 * @param key
	 * @param value
	 */
	public void updateCommonRealValue(String key,Object value) {
		commonrealDataMap.put(key, value);
	}
	/**
	 * 装入逆变器实时状态
	 * @param key
	 * @param value
	 */
	public void putState(String key, Object value) {
		stateMap.put(key, value);
	}
}
