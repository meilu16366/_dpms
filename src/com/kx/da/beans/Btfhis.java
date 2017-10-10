package com.kx.da.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.springframework.context.annotation.Lazy;

/**
 *  箱变历史数据
 */
@Entity
@Table(name="da_btf")
public class Btfhis implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**箱变编号*/
	private @Id Long btfid;
	/**配置于id之后*/
	@Lazy(value=true)
	@Formula("(select b.name from f_btf b where b.collectid=btfid)")
	private String name;

	/**采集时间*/
	private @Id Date ctime;
	/**有功功率*/
	private Double activePower;
	/**无功功率*/
	private Double reactivePower;
	/**功率因数*/
	private Double cos;
	/**AB线电压*/
	private Double uab;
	/**BC线电压*/
	private Double ubc;
	/**CA线电压*/
	private Double uca;
	/**A相电压*/
	private Double ua;
	/**B相电压*/
	private Double ub;
	/**C相电压*/
	private Double uc;
	/**A相电流*/
	private Double ia;
	/**B相电流*/
	private Double ib;
	/**C相电流*/
	private Double ic;
	/**A相有功功率*/
	private Double pa;
	/**B相有功功率*/
	private Double pb;
	/**C相有功功率*/
	private Double pc;
	/**A相无功功率*/
	private Double qa;
	/**B相无功功率*/
	private Double qb;
	/**C相无功功率*/
	private Double qc;
	/**A相视在功率*/
	private Double sa;
	/**A相视在功率*/
	private Double sb;
	/**C相视在功率*/
	private Double sc;
	/**总视在功率*/
	private Double s;
	/**电网频率*/
	private Double gridFrequency;
	/**正向有功电能*/
	private Double gmpPow;
	/**反向有功电能*/
	private Double gmnPow;
	/**正向无功电能*/
	private Double gmpReaPow;
	/**反向无功电能*/
	private Double gmnReaPow;

	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Double getActivePower() {
		return activePower;
	}
	public void setActivePower(Double activePower) {
		this.activePower = activePower;
	}
	public Double getReactivePower() {
		return reactivePower;
	}
	public void setReactivePower(Double reactivePower) {
		this.reactivePower = reactivePower;
	}
	public Double getCos() {
		return cos;
	}
	public void setCos(Double cos) {
		this.cos = cos;
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
	public Double getUa() {
		return ua;
	}
	public void setUa(Double ua) {
		this.ua = ua;
	}
	public Double getUb() {
		return ub;
	}
	public void setUb(Double ub) {
		this.ub = ub;
	}
	public Double getUc() {
		return uc;
	}
	public void setUc(Double uc) {
		this.uc = uc;
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
	public Double getPa() {
		return pa;
	}
	public void setPa(Double pa) {
		this.pa = pa;
	}
	public Double getPb() {
		return pb;
	}
	public void setPb(Double pb) {
		this.pb = pb;
	}
	public Double getPc() {
		return pc;
	}
	public void setPc(Double pc) {
		this.pc = pc;
	}
	public Double getQa() {
		return qa;
	}
	public void setQa(Double qa) {
		this.qa = qa;
	}
	public Double getQb() {
		return qb;
	}
	public void setQb(Double qb) {
		this.qb = qb;
	}
	public Double getQc() {
		return qc;
	}
	public void setQc(Double qc) {
		this.qc = qc;
	}
	public Double getSa() {
		return sa;
	}
	public void setSa(Double sa) {
		this.sa = sa;
	}
	public Double getSb() {
		return sb;
	}
	public void setSb(Double sb) {
		this.sb = sb;
	}
	public Double getSc() {
		return sc;
	}
	public void setSc(Double sc) {
		this.sc = sc;
	}
	public Double getS() {
		return s;
	}
	public void setS(Double s) {
		this.s = s;
	}
	public Double getGridFrequency() {
		return gridFrequency;
	}
	public void setGridFrequency(Double gridFrequency) {
		this.gridFrequency = gridFrequency;
	}
	public Double getGmpPow() {
		return gmpPow;
	}
	public void setGmpPow(Double gmpPow) {
		this.gmpPow = gmpPow;
	}
	public Double getGmnPow() {
		return gmnPow;
	}
	public void setGmnPow(Double gmnPow) {
		this.gmnPow = gmnPow;
	}
	public Double getGmpReaPow() {
		return gmpReaPow;
	}
	public void setGmpReaPow(Double gmpReaPow) {
		this.gmpReaPow = gmpReaPow;
	}
	public Double getGmnReaPow() {
		return gmnReaPow;
	}
	public void setGmnReaPow(Double gmnReaPow) {
		this.gmnReaPow = gmnReaPow;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getBtfid() {
		return btfid;
	}
	public void setBtfid(Long btfid) {
		this.btfid = btfid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((btfid == null) ? 0 : btfid.hashCode());
		result = prime * result + ((ctime == null) ? 0 : ctime.hashCode());
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
		Btfhis other = (Btfhis) obj;
		if (btfid == null) {
			if (other.btfid != null)
				return false;
		} else if (!btfid.equals(other.btfid))
			return false;
		if (ctime == null) {
			if (other.ctime != null)
				return false;
		} else if (!ctime.equals(other.ctime))
			return false;
		return true;
	}
	
	
}
