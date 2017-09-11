package com.kx.da.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
@Entity
@Table(name="da_eventhis")
public class Eventhis implements Serializable {
	
	private static final long serialVersionUID= 1L; 
	
	/**事件编号*/
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long id;
	/**采集编号*/
	private Integer collectid;
	/**设备类型*/
	private String  eqtype;
	/**采集时间*/
	private Date ctime;
	/**设备名称*/
	private String eqname;
	/**事件类型 1操作事件 2遥信变位 3遥测越限 4其他*/
	private Integer eventtype;
	/**事件内容*/
	private String describes;
	/**事件动作 0由合到分 1由分到合 2执行成功 3选择成功*/
	private Integer action;
	/**是否确认*/
	 @Type(type="yes_no")
	private boolean sure;
	/**是否SOE*/
	 @Type(type="yes_no")
	private boolean issoe;
	/**事件等級 1.提示  2.警告 3.严重*/
	private Integer elevel;
	/**测点id*/
	private String mid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCollectid() {
		return collectid;
	}
	public void setCollectid(Integer collectid) {
		this.collectid = collectid;
	}
	public String getEqtype() {
		return eqtype;
	}
	public void setEqtype(String eqtype) {
		this.eqtype = eqtype;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public String getEqname() {
		return eqname;
	}
	public void setEqname(String eqname) {
		this.eqname = eqname;
	}
	public Integer getEventtype() {
		return eventtype;
	}
	public void setEventtype(Integer eventtype) {
		this.eventtype = eventtype;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public boolean isSure() {
		return sure;
	}
	public void setSure(boolean sure) {
		this.sure = sure;
	}
	public boolean isIssoe() {
		return issoe;
	}
	public void setIssoe(boolean issoe) {
		this.issoe = issoe;
	}
	
	public Integer getElevel() {
		return elevel;
	}
	public void setElevel(Integer elevel) {
		this.elevel = elevel;
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
		Eventhis other = (Eventhis) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	
}
