package com.kx.base.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.math.NumberUtils;

@Entity
@Table(name="f_inverterstateconf")
public class InverterStateConf implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long id;
	/**逆变器型号*/
	private String model;
	/**正常运行*/
	private String normalrun;
	/**正常停机*/
	private String normalstop;
	/**告警运行*/
	private String errorrun;
	/**故障停机*/
	private String errorstop;
	/**通讯中断*/
	private String bread;
	public String getNormalrun() {
		return normalrun;
	}
	public void setNormalrun(String normalrun) {
		this.normalrun = normalrun;
	}
	public String getNormalstop() {
		return normalstop;
	}
	public void setNormalstop(String normalstop) {
		this.normalstop = normalstop;
	}
	public String getErrorrun() {
		return errorrun;
	}
	public void setErrorrun(String errorrun) {
		this.errorrun = errorrun;
	}
	public String getErrorstop() {
		return errorstop;
	}
	public void setErrorstop(String errorstop) {
		this.errorstop = errorstop;
	}
	public String getBread() {
		return bread;
	}
	public void setBread(String bread) {
		this.bread = bread;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		InverterStateConf other = (InverterStateConf) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * 获得对应的break测点
	 * @return
	 */
	public String getBreakPoint(long id) {
		if(bread == null) {
			return null;
		}
		String[] every = bread.split(";");
		for(String one : every) {
			String[] prs = one.split("=");
			if(prs.length==1) {
				continue;
			}
			String[] idd = prs[1].split(",");
			for(String ids : idd) {
				String[] oid = ids.split("-");
				long s = NumberUtils.toLong(oid[0]);
				long e = NumberUtils.toLong(oid.length>1?oid[1]:oid[0]);
				if( id>=s && id<=e ) {
					return prs[0];
				}
			}
		}
		return null;
	}

}
