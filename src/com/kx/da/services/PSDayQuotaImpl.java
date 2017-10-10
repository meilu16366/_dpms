package com.kx.da.services;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kx.base.beans.ElectricityMeter;
import com.kx.base.beans.EmiInfo;
import com.kx.da.beans.PSquotaDay;
import com.kx.frame.def.CollectDef;
import com.kx.frame.services.IBaseDao;
import com.kx.frame.utils.SysParamUtil;


@SuppressWarnings({"rawtypes","unchecked"})
@Component("psDayQuota")
public class PSDayQuotaImpl implements PSDayQuota {
	
	private static Logger logger = Logger.getLogger(PSDayQuotaImpl.class);
	
	@Autowired
	private IBaseDao daoSrv;
	
	@Override
	public boolean calc(Date date) {
		//1逆变器发电量
		PSquotaDay quotaday = new PSquotaDay();
		quotaday.setCtime(date);
		String sql = " select sum(daycap) from da_inverterday where ctime='"+DateFormatUtils.format(date, "yyyy-MM-dd")+"' ";
		//Object nbqdayCap = daoSrv.getSqlQuery(sql).setDate(0, date).uniqueResult();
		Object nbqdayCap = daoSrv.findOneRowBySql(sql);
		quotaday.setNbqdaycap(NumberUtils.toDouble(nbqdayCap+""));
		//2线路表发电量
		String hql = "from ElectricityMeter where type=3";
		List<ElectricityMeter> xldbs = daoSrv.findByHql(hql);
		Double xldballcap = 0d;
		for(ElectricityMeter db : xldbs) {
			String column = "BackwardP";//反向有功
			if(db.getSumway()==1) {//正向有功
				column = "ForwardP";
			}
			sql = "select max("+column+")-min("+column+") as power from DATA_DBHIS "
					+ "where dbid="+db.getCollectid()+" and collecttime>='"+DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss")+"' "
							+ "and collecttime<'"+DateFormatUtils.format(DateUtils.addDays(date, 1), "yyyy-MM-dd HH:mm:ss")+"' ";
			Object dbdayCap=daoSrv.findOneRowBySql(sql);
			xldballcap += NumberUtils.toDouble(dbdayCap+"")*db.getRate();
			
		}
		quotaday.setDbdaycap(xldballcap);
		//并网点发电量
		hql = "from ElectricityMeter where type=1";
		List<ElectricityMeter> miandbs = daoSrv.findByHql(hql);
		Double bwallcap = 0d;
		for(ElectricityMeter db : miandbs) {
			String column = "BackwardP";//反向有功
			if(db.getSumway()==1) {//正向有功
				column = "ForwardP";
			}
			sql = "select max("+column+")-min("+column+") as power from DATA_DBHIS "
					+ "where dbid="+db.getCollectid()+" and collecttime>='"+DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss")+"' "
							+ "and collecttime<'"+DateFormatUtils.format(DateUtils.addDays(date, 1), "yyyy-MM-dd HH:mm:ss")+"' ";
			Object dbdayCap = daoSrv.findOneRowBySql(sql);
			bwallcap += NumberUtils.toDouble(dbdayCap+"") * db.getRate();
		}
		quotaday.setBwdaycap(bwallcap);
		//满发小时数
		//总容量
		sql = "select sum(power) from f_inverter";
		Object totalPower = daoSrv.findOneRowBySql(sql);
		Double hour = (bwallcap / NumberUtils.toDouble(totalPower+""));
		quotaday.setHours(hour.isNaN()||hour.isInfinite()?0:hour);
		//逆变器最大出力及时间
		sql = " select t.ctime,t.acp from ("
				+ " select ctime,sum(ACPower) as acp from da_inverter "
				+ " where ctime>= '"+DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss")+"'"
				+ " and ctime<'"+DateFormatUtils.format(DateUtils.addDays(date, 1), "yyyy-MM-dd HH:mm:ss")+"' "
				+ "group by ctime"
				+ ") t  order by t.acp desc";
		List<Object[]>  invertermaxout = daoSrv.findBySql(sql);
		if(CollectionUtils.isNotEmpty(invertermaxout)) {
			quotaday.setMaxnbqpower(invertermaxout.get(0)==null?0:NumberUtils.toDouble(invertermaxout.get(0)[1]+""));
			quotaday.setMaxnbqtime(invertermaxout.get(0)==null?null:(Date)invertermaxout.get(0)[0]);
		}
		
		//并网点最大出力及时间
		sql = " select t.ctime,t.acp from ("
				+ " select ctime,sum(ActivePower) as acp from da_mergepoint "
				+ " where ctime>='"+DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss")+"'"
				+ " and ctime<'"+DateFormatUtils.format(DateUtils.addDays(date, 1), "yyyy-MM-dd HH:mm:ss")+"' "
				+ "group by ctime"
				+ ") t  order by t.acp desc";
		//Query bwquery = daoSrv.getSqlQuery(sql).setDate(0, date).setDate(1, DateUtils.addDays(date, 1));
		//Object[] bwmaxout = (Object[]) bwquery.setMaxResults(1).uniqueResult();
		
		List<Object[]>  bwmaxout = daoSrv.findBySql(sql);

		if(CollectionUtils.isNotEmpty(bwmaxout)) {
			quotaday.setMaxbwpower(bwmaxout.get(0)==null?0d:NumberUtils.toDouble(bwmaxout.get(0)[1]+""));
			quotaday.setMaxbwtime(bwmaxout.get(0)==null?null:(Date)bwmaxout.get(0)[0]);
		}
		
		//逆变器转换效率
		sql = "select avg(efficiency) from da_inverterday where ctime='"+DateFormatUtils.format(date, "yyyy-MM-dd")+"' ";
		//Object effient = daoSrv.getSqlQuery(sql).setDate(0, date).uniqueResult();
		Object effient = daoSrv.findOneRowBySql(sql);
		
		quotaday.setAvgnbqefficiency(NumberUtils.toDouble(effient+""));
		//减排CO2,
		quotaday.setCo2(quotaday.getBwdaycap() * CollectDef.DE_CO2);
		//节约标煤
		quotaday.setCoal(quotaday.getBwdaycap() * CollectDef.DE_COAL);
		//总辐射量
		//采集间隔分钟
		int cycle = 5;
		try {
			cycle = NumberUtils.toInt(SysParamUtil.getParameter(CollectDef.EMI_CYCLE).getValue());
		}catch(Exception e) {
			logger.info("未配置气象仪采集间隔！");
		}
		//取主气象仪
		hql = "from EmiInfo where mains='Y'";
		List<EmiInfo> emis = daoSrv.findByHql(hql);
		if(emis.size()>0) {
			EmiInfo qxy = emis.get(0);
			String column = "instRadiationTilt";
			if(qxy.getInittype()==1) {//水平
				column = "instRadiationIevel";
			}
			sql = " select sum("+column+"*"+cycle+"/60)*3.6/1000 from da_emi "
					+ " where emiid="+qxy.getCollectid()+" and collecttime>='"+DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss")+"' "
							+ "and collecttime<'"+DateFormatUtils.format(DateUtils.addDays(date, 1), "yyyy-MM-dd HH:mm:ss")+"' ";
			//Object toltalRadia = daoSrv.getSqlQuery(sql).setDate(0, date).setDate(1, DateUtils.addDays(date, 1)).uniqueResult();
			Object toltalRadia = daoSrv.findOneRowBySql(sql);
			quotaday.setTotalradia(NumberUtils.toDouble(toltalRadia+""));
		}
		sql = "delete from da_psquotaday where ctime='"+DateFormatUtils.format(date, "yyyy-MM-dd")+"' and psdayid<>0";
		daoSrv.execSql(sql);
		daoSrv.saveOrUpdate(quotaday);
		return true;
	}

}
