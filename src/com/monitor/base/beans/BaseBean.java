package com.monitor.base.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 基础表bean父类
 * @author Administrator
 *
 */
@Entity
public class BaseBean {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected @Id Long id;
	
	protected String name;
	
	protected String place;
	
	protected Long collectid;
	
	protected String remark;

	protected String models;

	protected String comply;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Long getCollectid() {
		return collectid;
	}

	public void setCollectid(Long collectid) {
		this.collectid = collectid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	public String getComply() {
		return comply;
	}

	public void setComply(String comply) {
		this.comply = comply;
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
		BaseBean other = (BaseBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
