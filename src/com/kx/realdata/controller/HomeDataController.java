package com.kx.realdata.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.base.beans.EmiInfo;
import com.kx.cache.services.Cache;
import com.kx.collect.services.MemoryHandler;
import com.kx.da.beans.PSquotaDay;
import com.kx.frame.def.CollectDef;
import com.kx.frame.def.KXFrameDef;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.sys.beans.Parameter;
import com.kx.frame.utils.DateUtil;
import com.kx.frame.utils.MapControllerUtil;
import com.kx.frame.utils.SysParamUtil;

@Controller
@RequestMapping("/realdata/home")
public class HomeDataController {
	@Autowired
	private IBaseDao daoSrv;
	@Autowired
	private MemoryHandler memoryHandler;
	@Autowired
	private Cache cache;
	@RequestMapping("/page")
	public String page() {
		return "/realdata/homepage";
	}
	
	//日负荷曲线
	@ResponseBody
	@RequestMapping("/radian")
	public Object radianLine(String date) throws ParseException {
		if(StringUtils.isEmpty(date)) {
			date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		}
		
		String sqlridian = "select  ctime, sum(instRadiationTilt) from da_emi where 1=1 ";
		
		Parameter param = SysParamUtil.getParameter(CollectDef.ACPOW_DATA);
		
		String sqlpower = "select ctime , sum("+param.getOption1()+") from "+param.getValue()+" where 1=1 ";
		
		sqlpower += " and ctime>='"+date+" "+CollectDef.RADIATION_START+ "' ";
		sqlpower += " and ctime<='"+date+" "+CollectDef.RADIATION_END+"' ";
		
		sqlridian += " and ctime>='"+date+" "+CollectDef.RADIATION_START+"' ";
		sqlridian += " and ctime<='"+date+" "+CollectDef.RADIATION_END+"' ";
		sqlridian+= "group by ctime";
		sqlridian += "  order by ctime";
		sqlpower+= "group by ctime";
		sqlpower += "  order by ctime";
		List<Object[]> acpower=daoSrv.findBySql(sqlpower);
		List<Object[]>   instRadiationTilt =daoSrv.findBySql(sqlridian);
		 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date  s=sd.parse(date+" "+CollectDef.RADIATION_START);
		 Date  e=sd.parse(date+" "+CollectDef.RADIATION_END);
		 
		Map<String, Object> radiamap = MapControllerUtil.getData( s,e,15, "yyyy-MM-dd HH:mm:ss");
		Map<String, Object> mapacpower = MapControllerUtil.getData( s,e,15, "yyyy-MM-dd HH:mm:ss");
		
		List<String> xdata = new ArrayList<String>();
		List<Object> acpowers = new ArrayList<Object>();
		List<Object> ridain = new ArrayList<Object>();
	
		for(Object[] a : acpower) {
			mapacpower.put(DateFormatUtils.format((Date)a[0],"yyyy-MM-dd HH:mm:ss"), NumberUtils.toDouble(a[1]+""));
		}
		
		
		for(Object[] ins : instRadiationTilt) {
			radiamap.put(DateFormatUtils.format((Date)ins[0],"yyyy-MM-dd HH:mm:ss"), NumberUtils.toDouble(ins[1]+""));
		}
		for(String key: radiamap.keySet()) {
			xdata.add(key.split(" ")[1].substring(0,key.split(" ")[1].lastIndexOf(":")));
			ridain.add(radiamap.get(key)==null?"-":radiamap.get(key));
		}
		for(String key: mapacpower.keySet()) {
			acpowers.add(mapacpower.get(key)==null?"-":mapacpower.get(key));
		}
		
		return new Object[] {xdata,acpowers,ridain};
	}
	
	//月发电量统计
	@ResponseBody
	@RequestMapping("/monthcap")
	public Object monthCapBar(String month) throws Exception {
		if(StringUtils.isEmpty(month)) {
			month = DateFormatUtils.format(new Date(), "yyyy-MM");
		}
		
		SysParamUtil.getParameter("");
		
		String hql = "from PSquotaDay";
		hql += " where ctime>='"+month+"-01' ";
		hql += " and ctime<='"+month+"-"+DateUtil.getMonthLastDay(month)+"' ";
		List<PSquotaDay> psday=daoSrv.findByHql(hql);
		
		 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		 Date  s=sd.parse(month+"-01");
		 Date  e=sd.parse(month+"-"+DateUtil.getMonthLastDay(month));
		 
		Map<String, Object> daycapmap = MapControllerUtil.getData( s,e,24*60, "yyyy-MM-dd");
		Map<String, Object>  radiamap= MapControllerUtil.getData( s,e,24*60, "yyyy-MM-dd");
		
		List<String> xdata = new ArrayList<String>();
		List<Object> daycap = new ArrayList<Object>();
		List<Object> totalradia = new ArrayList<Object>();
		String equipment=SysParamUtil.getParameter("equipment").getValue();
		for(PSquotaDay a : psday) {
			if(equipment.equals("DB")) {
				daycapmap.put(DateFormatUtils.format(a.getCtime(),"yyyy-MM-dd"), a.getDbdaycap());
			}else if(equipment.equals("BWD")) {
				daycapmap.put(DateFormatUtils.format(a.getCtime(),"yyyy-MM-dd"), a.getBwdaycap());
			}else {
				daycapmap.put(DateFormatUtils.format(a.getCtime(),"yyyy-MM-dd"), a.getNbqdaycap());
			}
			radiamap.put(DateFormatUtils.format(a.getCtime(),"yyyy-MM-dd"), a.getTotalradia());
		}
		
		for(String key: radiamap.keySet()) {
			xdata.add(key.split("-")[2]);
			daycap.add(daycapmap.get(key)==null?"-":(Double)daycapmap.get(key));
			totalradia.add(radiamap.get(key)==null?"-":(Double)radiamap.get(key));
		}
		return new Object[] {xdata,daycap,totalradia};
		
	}
	
	@ResponseBody
	@RequestMapping("/data")
	public Object totalData() throws Exception {
		Map<String,Object> datas = new HashMap<String,Object>();
		//交流功率
		Double acpower = (Double) memoryHandler.getCommonRealValue(CollectDef.PS_ACPOWER);
		datas.put("acpower", acpower==null?0:acpower);
		//日发电量
		Double daycap = (Double) memoryHandler.getCommonRealValue(CollectDef.PS_DAYCAP);
		datas.put("daycap", daycap==null?0:daycap);
		//月发电量
		String equipment=SysParamUtil.getParameter("equipment").getValue();
		String column = "";
		if(equipment.equals("DB")) {
			column = "dbdaycap";
		}else if(equipment.equals("BWD")) {
			column = "bwdaycap";
		}else {
			column = "nbqdaycap";
		}
		String month = DateFormatUtils.format(new Date(), "yyyy-MM");
		String sql = "select sum("+column +") from da_psquotaday ";
		sql += " where ctime>='"+month+"-01' ";
		sql += " and ctime<='"+month+"-"+DateUtil.getMonthLastDay(month)+"' ";
		Double monthcap = NumberUtils.toDouble(daoSrv.findOneRowBySql(sql)+"");
		datas.put("monthcap", (monthcap==null?0:monthcap)+(Double)datas.get("daycap"));
		//总发电量
		sql = "select sum("+column +") from da_psquotaday";
		Double totalcap = NumberUtils.toDouble(""+daoSrv.findOneRowBySql(sql));
		datas.put("totalcap", (totalcap==null?0:totalcap)+(Double)datas.get("daycap"));
		//瞬时辐射
		Double ridian = (Double) memoryHandler.getRealValue(CollectDef.EMI_NOWRADIAN);
		datas.put("ridian", ridian==null?0:ridian);
		//日辐射量
		Double ridianq = (Double) memoryHandler.getRealValue(CollectDef.EMI_TOTALRADIAN);
		// 没有总辐射量
		if(ridianq==null || ridianq == 0) {
			//采集间隔分钟
			int cycle = 5;
			try {
				cycle = NumberUtils.toInt(SysParamUtil.getParameter(CollectDef.EMI_CYCLE).getValue());
			}catch(Exception e) {}
			//取主气象仪
			String hql = "from EmiInfo where mains='Y'";
			List<EmiInfo> emis = daoSrv.findByHql(hql);
			if(emis.size()>0) {
				EmiInfo qxy = emis.get(0);
				column = "instRadiationTilt";
				if(qxy.getInittype()==1) {//水平
					column = "instRadiationIevel";
				}
				Date date = DateUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"),new String[] {"yyyy-MM-dd"});
				sql = " select sum("+column+"*"+cycle+"/60)*3.6/1000 from da_emi "
						+ " where emiid="+qxy.getCollectid()+" and collecttime>='"+DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss")+"' "
						+ "and collecttime<'"+DateFormatUtils.format(DateUtils.addDays(date, 1), "yyyy-MM-dd HH:mm:ss")+"' ";
				ridianq = NumberUtils.toDouble(daoSrv.findOneRowBySql(sql)+"");
			}
		}
		datas.put("ridianq", ridianq==null?0:ridianq);
		Double tempre = (Double) memoryHandler.getRealValue(CollectDef.EMI_TEMPERATURE);
		datas.put("tempre", tempre==null?0:tempre);
		
		//二氧化碳
		datas.put("co2", (Double)datas.get("totalcap")*CollectDef.DE_CO2);
		//标准煤
		datas.put("coal", (Double)datas.get("totalcap")*CollectDef.DE_COAL);
		//SO2
		datas.put("so2", (Double)datas.get("totalcap")*CollectDef.DE_SO2);
		//COX
		datas.put("cox", (Double)datas.get("totalcap")*CollectDef.DE_C0X);
		datas.put("capacity", NumberUtils.toDouble(KXFrameDef.CAPACITY,1));
		//逆变器
		Double nbqacpower = (Double) memoryHandler.getCommonRealValue(CollectDef.INVERTER_ACPOWER);
		datas.put("nbqacpower", nbqacpower==null?0:nbqacpower);
		Double nbqdcpower = (Double) memoryHandler.getCommonRealValue(CollectDef.INVERTER_DCPOWER);
		datas.put("nbqdcpower", nbqdcpower==null?0:nbqdcpower);
		return datas;
	}
	
}
