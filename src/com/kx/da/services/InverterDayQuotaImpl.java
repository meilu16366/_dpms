package com.kx.da.services;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kx.da.beans.InverterQuotaDay;
import com.kx.frame.services.IBaseDao;


@SuppressWarnings({"rawtypes","unchecked"})
@Component("inverterDayQuota")
public class InverterDayQuotaImpl implements InverterDayQuota {
	
	@Autowired
	private IBaseDao daoSrv;
	
	@Override
	public boolean calc(Long inverterid,Date date) {
		
		try {
			String sql = "select max(a.DayCapacity) as dc,"
					+ " sum(a.ACPower)/sum(a.dcpower) as effient,"
					+ " max(a.DayCapacity)/b.power as h,"
					+ " max(a.dcpower) as mdc,"
					+ " max(a.acpower) as mac,"
					+ " avg(a.InvertorTemp) as at,a.inverterid from data_nbqhis a left join info_nbq b on a.inverterid=b.collectid "
					+ " where a.ctime>='"+DateFormatUtils.format(date, "yyyy-MM-dd")+" 11:00:00' "
					+ " and a.ctime<='"+DateFormatUtils.format(date, "yyyy-MM-dd")+" 23:00:00'";
			if(inverterid != null) {
				sql += " and a.inverterid="+inverterid;
			}
			sql += " group by a.inverterid ";
		
			List<Object[]> quotas = daoSrv.findBySql(sql);
			
			//1总发电量,2总转换效率,3总满发小时,4最大支流功率,5最大交流功率,6总温度
			for(Object[] os : quotas ) {
				InverterQuotaDay quota = new InverterQuotaDay();
				quota.setCtime(date);
				quota.setInverterdayid(Long.valueOf(os[6]+""));
				quota.setDaycap(NumberUtils.toDouble(os[0]+""));
				quota.setEfficiency(NumberUtils.toDouble(os[1]+"")*100);
				quota.setHour(NumberUtils.toDouble(os[2]+""));
				quota.setMaxdcpower(NumberUtils.toDouble(os[3]+""));
				quota.setMaxacpower(NumberUtils.toDouble(os[4]+""));
				quota.setAvgtemperature(NumberUtils.toDouble(os[5]+""));
				daoSrv.saveOrUpdate(quota);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
