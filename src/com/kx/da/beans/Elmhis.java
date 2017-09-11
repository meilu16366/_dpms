package com.kx.da.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.springframework.context.annotation.Lazy;

/**
 * 电表
 * @author Administrator
 *
 */
@Entity
@Table(name="da_elm")
public class Elmhis implements Serializable {
	
	private static final long serialVersionUID= 1L; 
	
	/**电表编号*/
	private @Id long elmid;
	/**电表名称*/
	@Lazy(value=true)
	@Formula("(select d.name from f_elm d where d.collectid = elmid )")
	private String name;
	/**采集时间*/
	private @Id Date collecttime;
	/**正向有功*/
	private Double forwardP;
	/**正向无功*/
	private Double forwardReaP;
	/**反向有功*/
	private Double backwardP;
	/**反向无功*/
	private Double backwardReaP;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCollecttime() {
		return collecttime;
	}
	public void setCollecttime(Date collecttime) {
		this.collecttime = collecttime;
	}
	public Double getForwardP() {
		return forwardP;
	}
	public void setForwardP(Double forwardP) {
		this.forwardP = forwardP;
	}
	public Double getForwardReaP() {
		return forwardReaP;
	}
	public void setForwardReaP(Double forwardReaP) {
		this.forwardReaP = forwardReaP;
	}
	public Double getBackwardP() {
		return backwardP;
	}
	public void setBackwardP(Double backwardP) {
		this.backwardP = backwardP;
	}
	public Double getBackwardReaP() {
		return backwardReaP;
	}
	public void setBackwardReaP(Double backwardReaP) {
		this.backwardReaP = backwardReaP;
	}
	public long getElmid() {
		return elmid;
	}
	public void setElmid(long elmid) {
		this.elmid = elmid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collecttime == null) ? 0 : collecttime.hashCode());
		result = prime * result + (int) (elmid ^ (elmid >>> 32));
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
		Elmhis other = (Elmhis) obj;
		if (collecttime == null) {
			if (other.collecttime != null)
				return false;
		} else if (!collecttime.equals(other.collecttime))
			return false;
		if (elmid != other.elmid)
			return false;
		return true;
	}

	
}
