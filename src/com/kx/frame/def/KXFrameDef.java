package com.kx.frame.def;

import com.kx.frame.utils.SysParamUtil;

public interface KXFrameDef {
	/**每页显示数*/
	String PAGE_SIZE = "pageSize";
	/**电站名称*/
	String PSNAME = SysParamUtil.getParameter("ps.info").getValue();
	/**电站容量*/
	String CAPACITY = SysParamUtil.getParameter("ps.info").getOption1();
	/**投产日期*/
	String RUN_DATE = SysParamUtil.getParameter("ps.info").getOption2();
	
}
