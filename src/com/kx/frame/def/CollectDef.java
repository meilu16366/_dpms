package com.kx.frame.def;

import org.apache.commons.lang.math.NumberUtils;

import com.kx.frame.sys.beans.Parameter;
import com.kx.frame.utils.SysParamUtil;

public interface CollectDef {
	/**组串逆变器电流配置*/
	int INVERTER_DCI = NumberUtils.toInt(SysParamUtil.getParameter("inverter.dci").getValue(),8);
	/**辐射开始时间*/
	String RADIATION_START = SysParamUtil.getParameter("radiation.start").getValue();
	/**辐射结束时间*/
	String RADIATION_END = SysParamUtil.getParameter("radiation.end").getValue();
	/**气象仪采集间隔*/
	String EMI_CYCLE = "EMI.cycle";
	/**功率查询选择*/
	String ACPOW_DATA ="ACPOWER.data";
	/**事件类型选择*/
	String EVENTTYPE ="eventType";
	/**组串逆变器支路偏低阈值*/
	double INVERTER_DCI_LOW = NumberUtils.toDouble(SysParamUtil.getParameter("inverter.dcilow").getValue(),0.8);
	
	/**节能减排CO2系数/t*/
	double DE_CO2 = 0.000997;
	/**节能减排标准煤系数/t*/
	double DE_COAL = 0.0004;
	/**SO2*/
	double DE_SO2 = 0.03/1000;
	/**碳氧化合物*/
	double DE_C0X = 0.015/1000;
	//发电量
	/**电站累计日发电量*/
	Parameter PS_DAYCAP_TYPE=SysParamUtil.getParameter("ps.daycap.type"); 
	/**当前交流总功率查询*/
	Parameter PS_ACPOWER_TYPE = SysParamUtil.getParameter("ps.acpower.type");
	/**当前总直流功率查询*/
	Parameter PS_DCPOWER_TYPE = SysParamUtil.getParameter("ps.dcpower.type");
	/**电站总交流功率*/
	String PS_ACPOWER = "ps_acpower";
	/**电站总直流功率*/
	String PS_DCPOWER = "ps_dcpower";
	/**电站总日发*/
	String PS_DAYCAP = "ps_daycap";
	
	/**电站累计发电*/
	String PS_ALLCAP = "ps_allcap";
	
	
	/**当日满发小时数*/
	String PS_HOURS = "ps_hours";
	/**逆变器转换效率*/
	String PS_INVERTER_EFFICIENCY = "ps_inverter_efficiency";
	//节能减排
	/**累计节约标准煤*/
	String PS_TOTALCOAL = "ps_totalcoal";
	/**日节约标准煤*/
	String PS_DAYCOAL = "ps_daycoal";
	/**日减排二氧化碳*/
	String PS_CO2 = "ps_co2";
	//气象仪
	/**气象仪瞬时辐射强度*/
	String EMI_NOWRADIAN = SysParamUtil.getParameter("now.radian").getValue();
	/**气象仪总辐射量*/
	String EMI_TOTALRADIAN = SysParamUtil.getParameter("total.radian").getValue();
	/**环境温度*/
	String EMI_TEMPERATURE = SysParamUtil.getParameter("h.temperature").getValue();
	/**组件温度*/
	String EMI_MODETEMP = SysParamUtil.getParameter("m.temperature").getValue();
	
	//逆变器
	/**逆变器总交流功率*/
	String INVERTER_ACPOWER = "inverter_ACPower";
	/**逆变器总直流功率*/
	String INVERTER_DCPOWER = "inverter_DCPower";
	/**逆变器总日发电量*/
	String INVERTER_DAYCAP = "inverter_dayCap";
	/**逆变器总转换效率*/
	String INVERTER_EFFICIENCY = "inverter_efficiency";
	/**逆变器总等效小时数*/
	String INVERTER_HOURS = "inverter_hours";
	/**状态 1_NBQ_YC_1_state*/
	String INVERTER_STATE = "InverterState";
	
	String INVERTER_TOTALCAP = "Inverter_totalCap";
	
	/**日发电量*/
	String INVERTER_NOWDAYCAP=SysParamUtil.getParameter("inverter.daycap").getValue();
	/**总发电量*/
	String INVERTER_NOWTOTALCAP=SysParamUtil.getParameter("inverter.totalcap").getValue();
	/**交流功率*/
	String INVERTER_NOWACPOWER=SysParamUtil.getParameter("inverter.acpower").getValue();
	/**直流功率*/
	String INVERTER_NOWDCPOWER=SysParamUtil.getParameter("inverter.dcpower").getValue();
	/**逆变器支路*/
	String INVERTER_BRANCH = SysParamUtil.getParameter("inverter.branch").getValue();
	/**环境监测仪平均辐射量的百分比*/
	String EMI_AVGRADIANPERCENT=SysParamUtil.getParameter("EMI.radianpercent").getValue();
}
