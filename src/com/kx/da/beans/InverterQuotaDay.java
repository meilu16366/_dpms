package com.kx.da.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 逆变器日报
 */
@Entity
@Table(name="da_inverterday")
public class InverterQuotaDay implements Serializable {

	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long inverterdayid;
	
	private Long inverterid;
	
	@Lazy(value=true)
	@Formula("(select t.name from f_inverter t where t.collectid=inverterid)")
	private String name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ctime;
	/**日发电量*/
	private Double daycap;
	/**转换效率*/
	private Double efficiency;
	/**满发小时数*/
	private Double hour;
	/**最大直流功率*/
	private Double maxdcpower;
	/**最大交流功率*/
	private Double maxacpower;
	/**平均温度*/
	private Double avgtemperature;

	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Double getDaycap() {
		return daycap;
	}
	public void setDaycap(Double daycap) {
		this.daycap = daycap;
	}
	public Double getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(Double efficiency) {
		this.efficiency = efficiency;
	}
	public Double getHour() {
		return hour;
	}
	public void setHour(Double hour) {
		this.hour = hour;
	}
	public Double getMaxdcpower() {
		return maxdcpower;
	}
	public void setMaxdcpower(Double maxdcpower) {
		this.maxdcpower = maxdcpower;
	}
	public Double getMaxacpower() {
		return maxacpower;
	}
	public void setMaxacpower(Double maxacpower) {
		this.maxacpower = maxacpower;
	}
	public Double getAvgtemperature() {
		return avgtemperature;
	}
	public void setAvgtemperature(Double avgtemperature) {
		this.avgtemperature = avgtemperature;
	}
	public Long getInverterdayid() {
		return inverterdayid;
	}
	public void setInverterdayid(Long inverterdayid) {
		this.inverterdayid = inverterdayid;
	}
	public Long getInverterid() {
		return inverterid;
	}
	public void setInverterid(Long inverterid) {
		this.inverterid = inverterid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inverterdayid == null) ? 0 : inverterdayid.hashCode());
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
		InverterQuotaDay other = (InverterQuotaDay) obj;
		if (inverterdayid == null) {
			if (other.inverterdayid != null)
				return false;
		} else if (!inverterdayid.equals(other.inverterdayid))
			return false;
		return true;
	}

	
}
