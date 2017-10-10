package com.kx.da.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.springframework.context.annotation.Lazy;
/**
 * 气象仪
 * @author Administrator
 *
 */
@Entity
@Table(name="da_emi")
public class Emihis implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**气象仪id*/
	private @Id Long emiid;
	@Lazy(value=true)
	@Formula("(select q.name from f_emi q where q.collectid=emiid )")
	private String name;
	/**采集时间*/
	private @Id Date ctime;
	/**环境湿度*/
	private Double humidity;
	/**环境温度*/
	private Double temperature;
	/**组件温度*/
	private Double panelTemp;
	/**瞬时辐射强度（水平）*/
	private Double instRadiationIevel;
	/**瞬时辐射强度（倾斜）*/
	private Double instRadiationTilt;
	/**累计辐射（水平）*/
	private Double allRadiationIevel; 
	/**累计辐射（倾斜）*/
	private Double allRadiationTilt;
	/**风速*/
	private Double windSpeed;
	/**风向*/
	private Double winDir;
	/**气压*/
	private Double pressure;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getPanelTemp() {
		return panelTemp;
	}
	public void setPanelTemp(Double panelTemp) {
		this.panelTemp = panelTemp;
	}
	public Double getInstRadiationIevel() {
		return instRadiationIevel;
	}
	public void setInstRadiationIevel(Double instRadiationIevel) {
		this.instRadiationIevel = instRadiationIevel;
	}
	public Double getInstRadiationTilt() {
		return instRadiationTilt;
	}
	public void setInstRadiationTilt(Double instRadiationTilt) {
		this.instRadiationTilt = instRadiationTilt;
	}
	public Double getAllRadiationIevel() {
		return allRadiationIevel;
	}
	public void setAllRadiationIevel(Double allRadiationIevel) {
		this.allRadiationIevel = allRadiationIevel;
	}
	public Double getAllRadiationTilt() {
		return allRadiationTilt;
	}
	public void setAllRadiationTilt(Double allRadiationTilt) {
		this.allRadiationTilt = allRadiationTilt;
	}
	public Double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public Double getWinDir() {
		return winDir;
	}
	public void setWinDir(Double winDir) {
		this.winDir = winDir;
	}
	public Double getPressure() {
		return pressure;
	}
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}
	public Long getEmiid() {
		return emiid;
	}
	public void setEmiid(Long emiid) {
		this.emiid = emiid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ctime == null) ? 0 : ctime.hashCode());
		result = prime * result + ((emiid == null) ? 0 : emiid.hashCode());
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
		Emihis other = (Emihis) obj;
		if (ctime == null) {
			if (other.ctime != null)
				return false;
		} else if (!ctime.equals(other.ctime))
			return false;
		if (emiid == null) {
			if (other.emiid != null)
				return false;
		} else if (!emiid.equals(other.emiid))
			return false;
		return true;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
