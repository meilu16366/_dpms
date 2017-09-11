package com.kx.da.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 异常设备
 */
@Entity
@Table(name="da_erroreq")
public class AEQdata implements Serializable,Comparable<AEQdata> {
	  
	private static final long serialVersionUID = 1L;
	
	/**编号*/
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long id;
	/**设备名称*/
	private String eqname;
	/**异常描述*/
	private String descript;
	/**发生时间*/
	private Date ctime;
	/**持续时间*/
	private Double keeptime;
	/**设备类型*/
	private String eqtype;
	/**采集编号*/
	private Integer collectid;
	public String getEqtype() {
		return eqtype;
	}
	public void setEqtype(String eqtype) {
		this.eqtype = eqtype;
	}
	public Integer getCollectid() {
		return collectid;
	}
	public void setCollectid(Integer collectid) {
		this.collectid = collectid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEqname() {
		return eqname;
	}
	public void setEqname(String eqname) {
		this.eqname = eqname;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Double getKeeptime() {
		return keeptime;
	}
	public void setKeeptime(Double keeptime) {
		this.keeptime = keeptime;
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
		AEQdata other = (AEQdata) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public int compareTo(AEQdata o) {
		Date odate = o.ctime;
		Date tdate = this.ctime;
		if(odate==null || tdate==null) {
			return 0;
		}
		if(odate.after(tdate)) {
			return -1;
		}else if(odate.before(tdate)){
			return 1;
		}else {
			return 0;
		}
	}
}
