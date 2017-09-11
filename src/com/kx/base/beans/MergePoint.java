package com.kx.base.beans;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *  并网点配置
 */
@Entity
@Table(name="f_mergePoint")
public class MergePoint implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private @Id Long id;
	
	/**电站id*/
	private Integer psid;
	/**名称*/
	private String name;
	/**生产厂家*/
	private String comply;
	/**安装位置*/
	private String place;
	/**采集编号*/
	private Integer  collectid;
	/**型号*/
	private String models;
	/**备注*/
	private String remark;
	
	public Integer getPsid() {
		return psid;
	}
	public void setPsid(Integer psid) {
		this.psid = psid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComply() {
		return comply;
	}
	public void setComply(String comply) {
		this.comply = comply;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Integer getCollectid() {
		return collectid;
	}
	public void setCollectid(Integer collectid) {
		this.collectid = collectid;
	}
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
		MergePoint other = (MergePoint) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	


	
}
