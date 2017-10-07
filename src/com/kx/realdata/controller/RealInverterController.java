package com.kx.realdata.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kx.base.beans.Inverter;
import com.kx.base.beans.SortParam;
import com.kx.cache.services.Cache;
import com.kx.collect.services.MemoryHandler;
import com.kx.frame.def.CollectDef;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.NumberFormatUtil;

@Controller
@RequestMapping("/realdata/inverter")
public class RealInverterController {
	@Autowired
	private IBaseDao daoSrv;
	@Autowired
	private MemoryHandler memoryHandler;
	@Autowired
	private Cache cache;
	@RequestMapping("/page")
	public String page(Model model) {
		List<Object> places = daoSrv.findBySql("select distinct(place) from f_inverter");
		model.addAttribute("places", places);
		return "/realdata/realinverter";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public Object realData(Integer state,String name,String place,Integer pageNo,Integer pageSize,final SortParam sortParam) {
		String hql = "from Inverter where 1=1 ";
		if(StringUtils.isNotEmpty(name)) {
			hql += " and name like '%"+ StringEscapeUtils.escapeSql(name) + "%' ";
		}
		if(StringUtils.isNotEmpty(place)) {
			hql += " and place='"+place+"'";
		}
		List<Inverter> pis = daoSrv.findByHql(hql + " order by collectid");
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		for(Inverter pi : pis) {
			Map<String,Object> one = new HashMap<String,Object>();
			one.put("id", pi.getId());
			one.put("_1", pi.getCollectid());
			one.put("_2", pi.getPlace());
			one.put("_3", pi.getName());
			one.put("_4", pi.getPower());
			String firstid = "1_NBQ_YC_"+pi.getCollectid()+"_";
			String dcpowerid = firstid + CollectDef.INVERTER_NOWDCPOWER;
			Double dcpower = (Double) memoryHandler.getRealValue(dcpowerid);
			one.put("_5",dcpower==null?0d:dcpower);//直流功率
			
			String acpowerid = firstid + CollectDef.INVERTER_NOWACPOWER;
			Double acpower = (Double) memoryHandler.getRealValue(acpowerid);
			
			one.put("_6", acpower==null?0d:acpower);//交流功率
			
			String daycapid = firstid + CollectDef.INVERTER_NOWDAYCAP;
			Double daycap = (Double) memoryHandler.getRealValue(daycapid);
			
			one.put("_7", daycap==null?0d:daycap);//日发电量
			
			String totalcapid = firstid + CollectDef.INVERTER_NOWTOTALCAP;
			Double totalcap = (Double) memoryHandler.getRealValue(totalcapid);
			
			one.put("_8", totalcap==null?0d:totalcap);//累计发电量
			Double efficen = (acpower==null?0:acpower) / (dcpower==null?0d:dcpower);
			one.put("_9", efficen.isInfinite() || efficen.isNaN()?0d:efficen);//转换效率
			String stateid = "1_NBQ_"+pi.getCollectid() + "_" + CollectDef.INVERTER_STATE;
			
			Integer nstate = (Integer) memoryHandler.getRealValue(stateid);
			if(nstate == null) {
				nstate = 5;
			}
			one.put("_10", nstate);
			if(state!=null && state!=6) {
				if(nstate == state) {
					datas.add(one);
				}
			}else if(state!=null && state==6){
				if(isDciError(pi.getCollectid(),nstate)) {
					datas.add(one);
				}
			}else {
				datas.add(one);
			}
		}
		//排序
		if(sortParam.isSort()) {
			Collections.sort(datas,new Comparator<Map<String,Object>>() {
				@Override
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					double d1 = NumberUtils.toDouble(""+o1.get(sortParam.getSort()));
					double d2 = NumberUtils.toDouble(""+o2.get(sortParam.getSort()));
					int rect = 0;
					if(d1>d2) {
						rect = 1;
					}else if(d1<d2) {
						rect = -1;
					}else {
						rect = 0;
					}
					if(sortParam.getOrder().equals("desc")) {
						rect = - rect;
					}
					return rect;
				}
			});
		}
		List<Object> results = new ArrayList<Object>();
		for(int i=(pageNo-1)*pageSize; i<pageNo*pageSize && i<datas.size(); i++) {
			results.add(datas.get(i));
		}
		Map<String,Object> total = new HashMap<String,Object>();
		total.put("total", datas.size());
		total.put("rows", datas);
		return total;
	}
	
	@ResponseBody
	@RequestMapping("/data")
	public Object totalData() {
		Map<String,Object> datas = new HashMap<String,Object>();
		//1.总功率
		Double acpower = (Double) memoryHandler.getCommonRealValue(CollectDef.INVERTER_ACPOWER);
		datas.put("acpower", acpower==null?0:acpower);
		//2.辐射强度
		Double ridian = (Double) memoryHandler.getRealValue(CollectDef.EMI_NOWRADIAN);
		datas.put("radian", ridian==null?0:ridian);
		//3.日发电量
		Double daycap = (Double) memoryHandler.getCommonRealValue(CollectDef.INVERTER_DAYCAP);
		datas.put("daycap", daycap==null?0:daycap);
		//4.总发电量
		Double totalcap = (Double) memoryHandler.getCommonRealValue(CollectDef.INVERTER_TOTALCAP);
		datas.put("totalcap", totalcap==null?0:totalcap);
		//5.状态统计
		List<Inverter> pis = cache.getInfos(Inverter.class);
		int[] states = {0,0,0,
						0,0,0,0};
		for(Inverter pi : pis) {
			String stateid = "1_NBQ_"+pi.getCollectid()+"_"+CollectDef.INVERTER_STATE;
			Integer state = (Integer) memoryHandler.getRealValue(stateid);
			if(state == null) {
				state = 5;
			}
			states[state]++;
			//判断支路异常
			if(isDciError(pi.getCollectid(),state)) {
				states[6]++;
			}
		}
		datas.put("states", states);
		datas.put("size", pis.size());
		return datas;
	}
	
	private boolean isDciError(Long cid,Integer state) {
		String firstid = "1_NBQ_YC_"+cid+"_";
		List<Double> totals = new ArrayList<Double>();
		double total = 0d;
		int count = 0;
		List<DCI> dcis = new ArrayList<DCI>();
		for(int i=1;i<=CollectDef.INVERTER_DCI;i++) {
			String dciid = firstid+CollectDef.INVERTER_BRANCH;
			Double val = (Double) memoryHandler.getRealValue(dciid);
			totals.add(val);
			total += val==null?0d:val;
			count ++;//TODO 处理备用情况
		}
		boolean isDciError = false;
		Double avg = total/count;
		for(int i=1;i<=CollectDef.INVERTER_DCI;i++) {
			Double val = totals.get(i-1);
			if(val==null) {
				val = 0d;
			}
			if(val==0) {
				isDciError = true;
				dcis.add(new DCI(i,"支路"+i+",电流为零"));
			}else if(val < avg*CollectDef.INVERTER_DCI_LOW){
				isDciError = true;
				dcis.add(new DCI(i,"支路"+i+",电流偏低,电流:"+NumberFormatUtil.format(val,2)));
			}else {
				dcis.add(new DCI(i,"支路"+i+",电流:"+val));
			}
		}
		
		return isDciError;
	}
	
	class DCI{
		private int index;//支路数
		private Object des;//描述
		
		public DCI(int index, Object des) {
			super();
			this.index = index;
			this.des = des;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public Object getDes() {
			return des;
		}
		public void setDes(Object des) {
			this.des = des;
		}
	}
	
}
