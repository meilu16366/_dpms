package com.kx.da.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 电站生产日报
 */
@Entity
@Table(name="da_psquotaday")
public class PSquotaDay implements Serializable{

	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long psdayid;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ctime;
	/**逆变器发电量*/
	private Double nbqdaycap;
	/**线路表发电量*/
	private Double dbdaycap;
	/**并网点发电量*/
	private Double bwdaycap;
	/**满发小时数*/
	private Double hours;
	/**逆变器最大出力*/
	private Double maxnbqpower;
	/**逆变器最大出力时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date maxnbqtime;
	/**并网点最大出力*/
	private Double maxbwpower;
	/**并网点最大出力时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date maxbwtime;
	/**逆变器平均转换效率*/
	private Double avgnbqefficiency;
	/**减排CO2*/
	private Double co2;
	/**节约标煤*/
	private Double coal;
	/**总辐射量*/
	private Double totalradia;
	
	public PSquotaDay() {
		
	}

	public PSquotaDay(Long psdayid, Date ctime, Double nbqdaycap, Double dbdaycap, Double bwdaycap, Double hours,
			Double maxnbqpower, Date maxnbqtime, Double maxbwpower, Date maxbwtime, Double avgnbqefficiency, Double co2,
			Double coal, Double totalradia) {
		super();
		this.psdayid = psdayid;
		this.ctime = ctime;
		this.nbqdaycap = nbqdaycap;
		this.dbdaycap = dbdaycap;
		this.bwdaycap = bwdaycap;
		this.hours = hours;
		this.maxnbqpower = maxnbqpower;
		this.maxnbqtime = maxnbqtime;
		this.maxbwpower = maxbwpower;
		this.maxbwtime = maxbwtime;
		this.avgnbqefficiency = avgnbqefficiency;
		this.co2 = co2;
		this.coal = coal;
		this.totalradia = totalradia;
	}
	public PSquotaDay(Date ctime) {
		this.ctime = ctime;
	}
	
	public Long getPsdayid() {
		return psdayid;
	}
	public void setPsdayid(Long psdayid) {
		this.psdayid = psdayid;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Double getNbqdaycap() {
		return nbqdaycap;
	}
	public void setNbqdaycap(Double nbqdaycap) {
		this.nbqdaycap = nbqdaycap;
	}
	public Double getDbdaycap() {
		return dbdaycap;
	}
	public void setDbdaycap(Double dbdaycap) {
		this.dbdaycap = dbdaycap;
	}
	public Double getBwdaycap() {
		return bwdaycap;
	}
	public void setBwdaycap(Double bwdaycap) {
		this.bwdaycap = bwdaycap;
	}
	public Double getHours() {
		return hours;
	}
	public void setHours(Double hours) {
		this.hours = hours;
	}
	public Double getMaxnbqpower() {
		return maxnbqpower;
	}
	public void setMaxnbqpower(Double maxnbqpower) {
		this.maxnbqpower = maxnbqpower;
	}
	public Date getMaxnbqtime() {
		return maxnbqtime;
	}
	public void setMaxnbqtime(Date maxnbqtime) {
		this.maxnbqtime = maxnbqtime;
	}
	public Double getMaxbwpower() {
		return maxbwpower;
	}
	public void setMaxbwpower(Double maxbwpower) {
		this.maxbwpower = maxbwpower;
	}
	public Date getMaxbwtime() {
		return maxbwtime;
	}
	public void setMaxbwtime(Date maxbwtime) {
		this.maxbwtime = maxbwtime;
	}
	public Double getAvgnbqefficiency() {
		return avgnbqefficiency;
	}
	public void setAvgnbqefficiency(Double avgnbqefficiency) {
		this.avgnbqefficiency = avgnbqefficiency;
	}
	public Double getCo2() {
		return co2;
	}
	public void setCo2(Double co2) {
		this.co2 = co2;
	}
	public Double getCoal() {
		return coal;
	}
	public void setCoal(Double coal) {
		this.coal = coal;
	}
	public Double getTotalradia() {
		return totalradia;
	}
	public void setTotalradia(Double totalradia) {
		this.totalradia = totalradia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((psdayid == null) ? 0 : psdayid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PSquotaDay other = (PSquotaDay) obj;
		if (psdayid == null) {
			if (other.psdayid != null)
				return false;
		} else if (!psdayid.equals(other.psdayid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PSquotaDay [psdayid=" + psdayid + ", ctime=" + ctime + ", nbqdaycap=" + nbqdaycap + ", dbdaycap="
				+ dbdaycap + ", bwdaycap=" + bwdaycap + ", hours=" + hours + ", maxnbqpower=" + maxnbqpower
				+ ", maxnbqtime=" + maxnbqtime + ", maxbwpower=" + maxbwpower + ", maxbwtime=" + maxbwtime
				+ ", avgnbqefficiency=" + avgnbqefficiency + ", co2=" + co2 + ", coal=" + coal + ", totalradia="
				+ totalradia + "]";
	}
	
	
}
