package com.kx.da.beans;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.springframework.context.annotation.Lazy;

/**
 * 逆变器
 * @author Administrator
 *
 */
@Entity
@Table(name="da_inverter")
public class Inverterhis implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**逆变器编号*/
	private @Id Long inverterid;
	
	/**逆变器名称*/
	/**配置于id之后*/
	@Lazy(value=true)
	@Formula("(select n.name from f_inverter n where n.collectid=inverterid)")
	private String name;
	/**采集时间*/
	private @Id Date ctime;
	/**总发电量*/
	private Double totalCapacity;
	/**日发电量*/
	private Double  dayCapacity;
	/**直流功率*/
	private Double dcPower;
	/**交流功率*/
	private Double acPower;
	/**AB线电压*/
	private Double uab;
	/**BC线电压*/
	private Double ubc;
	/**CA线电压*/
	private Double uca;
	/**A相电流*/
	private Double ia;
	/**B相电流*/
	private Double ib;
	/**C相电流*/
	private Double ic;
	/**电网频率*/
	private Double gridFrequency;
	/**功率因素*/
	private Double powerFactor;
	/**逆变器温度*/
	private Double invertorTemp;
	/**无功功率*/
	private Double reactivePower;
	/**支路电压1*/
	private Double dcu1;
	/**支路电压2*/
	private Double dcu2;
	/**支路电压3*/
	private Double dcu3;
	/**支路电压4*/
	private Double dcu4;
	/**支路电压5*/
	private Double dcu5;
	/**支路电压6*/
	private Double dcu6;
	/**支路电压7*/
	private Double dcu7;
	/**支路电压8*/
	private Double dcu8;
	/**支路电流1*/
	private Double dci1;
	/**支路电流2*/
	private Double dci2;
	/**支路电流3*/
	private Double dci3;
	/**支路电流4*/
	private Double dci4;
	/**支路电流5*/
	private Double dci5;
	/**支路电流6*/
	private Double dci6;
	/**支路电流7*/
	private Double dci7;
	/**支路电流8*/
	private Double dci8;
	/**转换效率*/
	private Double efficient;
	
	

	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Double getTotalCapacity() {
		return totalCapacity;
	}
	public void setTotalCapacity(Double totalCapacity) {
		this.totalCapacity = totalCapacity;
	}
	public Double getDayCapacity() {
		return dayCapacity;
	}
	public void setDayCapacity(Double dayCapacity) {
		this.dayCapacity = dayCapacity;
	}
	public Double getAcPower() {
		return acPower;
	}
	public void setAcPower(Double acPower) {
		this.acPower = acPower;
	}
	public Double getUab() {
		return uab;
	}
	public void setUab(Double uab) {
		this.uab = uab;
	}
	public Double getUbc() {
		return ubc;
	}
	public void setUbc(Double ubc) {
		this.ubc = ubc;
	}
	public Double getUca() {
		return uca;
	}
	public void setUca(Double uca) {
		this.uca = uca;
	}
	public Double getIa() {
		return ia;
	}
	public void setIa(Double ia) {
		this.ia = ia;
	}
	public Double getIb() {
		return ib;
	}
	public void setIb(Double ib) {
		this.ib = ib;
	}
	public Double getIc() {
		return ic;
	}
	public void setIc(Double ic) {
		this.ic = ic;
	}
	public Double getGridFrequency() {
		return gridFrequency;
	}
	public void setGridFrequency(Double gridFrequency) {
		this.gridFrequency = gridFrequency;
	}
	public Double getPowerFactor() {
		return powerFactor;
	}
	public void setPowerFactor(Double powerFactor) {
		this.powerFactor = powerFactor;
	}
	public Double getInvertorTemp() {
		return invertorTemp;
	}
	public void setInvertorTemp(Double invertorTemp) {
			this.invertorTemp = invertorTemp;
	}
	public Double getReactivePower() {
		return reactivePower;
	}
	public void setReactivePower(Double reactivePower) {
		this.reactivePower = reactivePower;
	}
	public Double getDcu1() {
		return dcu1;
	}
	public void setDcu1(Double dcu1) {
		this.dcu1 = dcu1;
	}
	public Double getDcu2() {
		return dcu2;
	}
	public void setDcu2(Double dcu2) {
		this.dcu2 = dcu2;
	}
	public Double getDcu3() {
		return dcu3;
	}
	public void setDcu3(Double dcu3) {
		this.dcu3 = dcu3;
	}
	public Double getDcu4() {
		return dcu4;
	}
	public void setDcu4(Double dcu4) {
		this.dcu4 = dcu4;
	}
	public Double getDcu5() {
		return dcu5;
	}
	public void setDcu5(Double dcu5) {
		this.dcu5 = dcu5;
	}
	public Double getDcu6() {
		return dcu6;
	}
	public void setDcu6(Double dcu6) {
		this.dcu6 = dcu6;
	}
	public Double getDcu7() {
		return dcu7;
	}
	public void setDcu7(Double dcu7) {
		this.dcu7 = dcu7;
	}
	public Double getDcu8() {
		return dcu8;
	}
	public void setDcu8(Double dcu8) {
		this.dcu8 = dcu8;
	}
	
	public Double getDci1() {
		return dci1;
	}
	public void setDci1(Double dci1) {
		this.dci1 = dci1;
	}
	public Double getDci2() {
		return dci2;
	}
	public void setDci2(Double dci2) {
		this.dci2 = dci2;
	}
	public Double getDci3() {
		return dci3;
	}
	public void setDci3(Double dci3) {
		this.dci3 = dci3;
	}
	public Double getDci4() {
		return dci4;
	}
	public void setDci4(Double dci4) {
		this.dci4 = dci4;
	}
	public Double getDci5() {
		return dci5;
	}
	public void setDci5(Double dci5) {
		this.dci5 = dci5;
	}
	public Double getDci6() {
		return dci6;
	}
	public void setDci6(Double dci6) {
		this.dci6 = dci6;
	}
	public Double getDci7() {
		return dci7;
	}
	public void setDci7(Double dci7) {
		this.dci7 = dci7;
	}
	public Double getDci8() {
		return dci8;
	}
	public void setDci8(Double dci8) {
		this.dci8 = dci8;
	}

	public Double getDcPower() {
		return dcPower;
	}
	public void setDcPower(Double dcPower) {
		this.dcPower = dcPower;
	}
	public Double getEfficient() {
		return efficient;
	}
	public void setEfficient(Double efficient) {
		this.efficient = efficient;
	}
	public Long getInverterid() {
		return inverterid;
	}
	public void setInverterid(Long inverterid) {
		this.inverterid = inverterid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inverterid == null) ? 0 : inverterid.hashCode());
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
		Inverterhis other = (Inverterhis) obj;
		if (inverterid == null) {
			if (other.inverterid != null)
				return false;
		} else if (!inverterid.equals(other.inverterid))
			return false;
		return true;
	}

}
