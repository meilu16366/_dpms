package com.kx.da.services;

import java.util.Date;


/**
 *  逆变器报表
 */
public interface InverterDayQuota {
	
	/**
	 * 计算日指标
	 * @param date 日期
	 * @return 计算成功
	 */
	boolean calc(Long inverterid,Date date);
	
}
